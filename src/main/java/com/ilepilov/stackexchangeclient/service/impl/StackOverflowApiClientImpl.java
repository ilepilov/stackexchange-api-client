package com.ilepilov.stackexchangeclient.service.impl;

import com.ilepilov.stackexchangeclient.domain.Tag;
import com.ilepilov.stackexchangeclient.repository.UserRepo;
import com.ilepilov.stackexchangeclient.request.UserRequestParams;
import com.ilepilov.stackexchangeclient.response.TagResponse;
import com.ilepilov.stackexchangeclient.domain.User;
import com.ilepilov.stackexchangeclient.response.UserResponse;
import com.ilepilov.stackexchangeclient.service.ApiClient;
import com.ilepilov.stackexchangeclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class StackOverflowApiClientImpl implements ApiClient {

  private static final String SITE = "stackoverflow";
  private static final String ACCESS_TOKEN = "fR4N19Mb4q2tWnubIPpGRg))";
  private static final String KEY = "B8rlUd1GEbaET1nI4voCRA((";

  private final Retrofit retrofit;
  private final UserService userService;
  private final UserRepo userRepo;

  public StackOverflowApiClientImpl(Retrofit retrofit, UserRepo userRepo) {
    this.retrofit = retrofit;
    this.userService = retrofit.create(UserService.class);
    this.userRepo = userRepo;
  }

  @Override
  public Page<User> getUsers(UserRequestParams params, Pageable pageable) {
    long count = userRepo.count();
    if (count > 0) {
      return userRepo.findAllByLocationAndTags(params, pageable);
    } else {
      List<User> usersFromApi = getUsersFromApi();
      userRepo.saveAll(usersFromApi);
      return userRepo.findAllByLocationAndTags(params, pageable);
    }
  }

  private List<User> getUsersFromApi() {
    String order = "desc";
    String sort = "reputation";
    int pageSize = 20;
    int min = 223;
    String filter = "!0ZJMqcs0DO6HRjwYw5v)iH856";

    List<User> allUsers = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      Call<UserResponse> getUsers = userService.getUsers(order, sort, SITE, i, pageSize, min, filter, ACCESS_TOKEN, KEY);
      try {
        Response<UserResponse> getUsersResponse = getUsers.execute();
        ResponseBody errorBody = getUsersResponse.errorBody();
        if (nonNull(errorBody)) {
          log.info("Get users request failed. Error body: {}", errorBody.string());
        }

        Optional<UserResponse> response = Optional.ofNullable(getUsersResponse.body());
        response.ifPresent(r -> {
          List<User> users = r.getItems();
          users.forEach(user -> {
            List<Tag> tags = getTags(user.getUserId());
            user.setTags(tags);
          });

          allUsers.addAll(users);

          Integer backOff = r.getBackOff();
          if (nonNull(backOff)) {
            sleep(backOff, 1001);
          }
        });
      } catch (IOException e) {
        log.error("Get users request failed:", e);
      }
    }

    return allUsers;
  }

  private List<Tag> getTags(Integer userId) {
    int page = 1;
    int pageSize = 100;
    String order = "desc";
    String sort = "name";
    String filter = "!-.BKsrtOfP3z";
    Call<TagResponse> getTags = userService.getTags(String.valueOf(userId), page, pageSize, order, sort,
      SITE, filter, ACCESS_TOKEN, KEY);

    try {
      Response<TagResponse> getTagsResponse = getTags.execute();
      if (nonNull(getTagsResponse.errorBody())) {
        ResponseBody responseBody = getTagsResponse.errorBody();
        log.info("Get tags request failed. Error body: {}", responseBody.string());
      }

      List<Tag> tags = new ArrayList<>();
      Optional<TagResponse> tagResponse = Optional.ofNullable(getTagsResponse.body());
      tagResponse.ifPresent(r -> {
        List<Tag> items = getTagsResponse.body().getItems();
        tags.addAll(items);

        Integer backOff = getTagsResponse.body().getBackOff();
        if (nonNull(r.getBackOff())) {
          sleep(2001, backOff);
        }
      });

      return tags;
    } catch (IOException e) {
      log.error("Get tags request failed: ", e);
    }

    return Collections.emptyList();
  }

  private void sleep(Integer delta, Integer backOff) {
    try {
      Thread.sleep(delta * backOff);
    } catch (InterruptedException e) {
      log.error("Sleep error:", e);
    }
  }
}
