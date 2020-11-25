package com.ilepilov.stackexchangeclient.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ApiResponse {
  @SerializedName("has_more")
  private Boolean hasMore;
  @SerializedName("backoff")
  private Integer backOff;
}
