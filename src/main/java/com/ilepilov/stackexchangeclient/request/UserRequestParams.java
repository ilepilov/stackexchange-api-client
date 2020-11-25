package com.ilepilov.stackexchangeclient.request;

import lombok.Data;

@Data
public class UserRequestParams {

  private String locationRegex;
  private String tagsRegex;
}
