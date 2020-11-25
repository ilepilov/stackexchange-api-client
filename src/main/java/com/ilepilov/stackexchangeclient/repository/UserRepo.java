package com.ilepilov.stackexchangeclient.repository;

import com.ilepilov.stackexchangeclient.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String>, UserRepoCustom {
}
