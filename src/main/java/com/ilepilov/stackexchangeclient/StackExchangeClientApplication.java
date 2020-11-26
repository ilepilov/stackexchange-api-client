package com.ilepilov.stackexchangeclient;

import com.ilepilov.stackexchangeclient.domain.User;
import com.ilepilov.stackexchangeclient.repository.UserRepo;
import com.ilepilov.stackexchangeclient.request.UserRequestParams;
import com.ilepilov.stackexchangeclient.service.ApiClient;
import com.ilepilov.stackexchangeclient.service.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class StackExchangeClientApplication implements CommandLineRunner {

  @Autowired
  public ApiClient apiClient;
  @Autowired
  public UserRepo userRepo;
  @Autowired
  private Writer writer;

  public static void main(String[] args) {
    SpringApplication.run(StackExchangeClientApplication.class, args);
  }

  @Override
  public void run(String... args) {
    Scanner scanner = new Scanner(System.in);
    int page = 0;
    int size = 5;

    while (true) {
      System.out.println("-------------------------------------------------------------------------------------------");
      PageRequest pageRequest = PageRequest.of(page, size);
      UserRequestParams userRequestParams = new UserRequestParams();
      userRequestParams.setLocationRegex(".*(United States|Germany|United Kingdom).*");
      userRequestParams.setTagsRegex("(windows|linux)");

      Page<User> users = apiClient.getUsers(userRequestParams, pageRequest);
      List<String> data = users.getContent().stream().map(User::toString).collect(Collectors.toList());

      writer.write(data);
      System.out.println("-------------------------------------------------------------------------------------------");
      System.out.printf("Page: %s", page + 1);
      System.out.println();
      System.out.printf("Total pages: %s", users.getTotalPages());
      System.out.println();
      System.out.printf("Total elements: %s", users.getTotalElements());
      System.out.println();

      page++;
      if (page == users.getTotalPages()) {
        break;
      }
      System.out.println("Next page?(y/n)");
      if (scanner.hasNext()) {
        String input = scanner.next();
        if (!input.equals("y")) {
          break;
        }
      } else {
        break;
      }
    }
  }
}
