package com.ilepilov.stackexchangeclient.repository;

import com.ilepilov.stackexchangeclient.domain.User;
import com.ilepilov.stackexchangeclient.request.UserRequestParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

public class UserRepoCustomImpl implements UserRepoCustom {

  private final MongoTemplate mongoTemplate;

  public UserRepoCustomImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Page<User> findAllByLocationAndTags(UserRequestParams userRequestParams, Pageable pageable) {
    final Query query = new Query();
    final List<Criteria> criteria = new ArrayList<>();

    if (userRequestParams.getLocationRegex() != null) {
      criteria.add(
        Criteria.where("location")
          .regex(userRequestParams.getLocationRegex()));
    }

    if (userRequestParams.getTagsRegex() != null) {
      criteria.add(
        Criteria.where("tags")
          .elemMatch(
            Criteria.where("name")
              .regex(userRequestParams.getTagsRegex())));
    }

    if (!criteria.isEmpty()) {
      query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
    }

    long count = mongoTemplate.count(query, User.class);
    List<User> users = mongoTemplate.find(query.with(pageable), User.class);
    return PageableExecutionUtils.getPage(users, pageable, () -> count);
  }
}
