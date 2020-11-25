package com.ilepilov.stackexchangeclient.repository;

import com.ilepilov.stackexchangeclient.domain.User;
import com.ilepilov.stackexchangeclient.request.UserRequestParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepoCustom {
  Page<User> findAllByLocationAndTags(UserRequestParams userRequestParams, Pageable pageable);
}
