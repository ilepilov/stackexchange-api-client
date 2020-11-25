package com.ilepilov.stackexchangeclient.response;

import com.ilepilov.stackexchangeclient.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class UserResponse extends ApiResponse {
  private List<User> items;
}
