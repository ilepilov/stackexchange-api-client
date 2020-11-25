package com.ilepilov.stackexchangeclient.service;

import com.ilepilov.stackexchangeclient.response.TagResponse;
import com.ilepilov.stackexchangeclient.response.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

  @GET("users")
  Call<UserResponse> getUsers(@Query("order") String order, @Query("sort") String sort, @Query("site") String site,
                              @Query("page") Integer page, @Query("pagesize") Integer pageSize, @Query("min") Integer min,
                              @Query("filter") String filter, @Query("access_token") String accessToken, @Query("key") String key);

  @GET("users/{ids}/tags")
  Call<TagResponse> getTags(@Path("ids") String ids,@Query("page") Integer page, @Query("pagesize") Integer pagesize, @Query("order") String order,
                            @Query("sort") String sort, @Query("site") String site, @Query("filter") String filter,
                            @Query("access_token") String accessToken, @Query("key") String key);
}
