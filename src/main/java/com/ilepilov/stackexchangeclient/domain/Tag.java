package com.ilepilov.stackexchangeclient.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class Tag {

  @Id
  @ToString.Exclude
  private String id;
  @Indexed
  private String name;
  @SerializedName("user_id")
  @ToString.Exclude
  private Integer userId;
}
