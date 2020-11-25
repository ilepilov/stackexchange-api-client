package com.ilepilov.stackexchangeclient.service;

import com.ilepilov.stackexchangeclient.domain.User;
import com.ilepilov.stackexchangeclient.request.UserRequestParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiClient {
  Page<User> getUsers(UserRequestParams params, Pageable pageable);
}
