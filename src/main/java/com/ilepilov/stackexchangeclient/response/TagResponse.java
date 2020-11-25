package com.ilepilov.stackexchangeclient.response;

import com.ilepilov.stackexchangeclient.domain.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class TagResponse extends ApiResponse {
  private List<Tag> items;
}
