package com.ilepilov.stackexchangeclient.service.impl;

import com.ilepilov.stackexchangeclient.service.Writer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOutWriterImpl implements Writer {

  @Override
  public void write(List<String> data) {
    data.forEach(System.out::println);
  }
}
