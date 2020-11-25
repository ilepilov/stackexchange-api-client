package com.ilepilov.stackexchangeclient.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("users")
public class User {
  @Id
  @ToString.Exclude
  private String id;

  @SerializedName("display_name")
  private String userName;
  private String location;
  @SerializedName("answer_count")
  private Integer answerCount;
  @SerializedName("question_count")
  private Integer questionCount;
  private List<Tag> tags;
  @SerializedName("link")
  private String linkToProfile;
  @SerializedName("profile_image")
  private String linkToAvatar;

  @SerializedName("account_id")
  private Integer accountId;
  private Integer reputation;
  @SerializedName("user_type")
  private String userType;
  @SerializedName("user_id")
  private Integer userId;
}
