package com.ilepilov.stackexchangeclient.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {

  @Value("${api.base.url}")
  private String apiBaseUrl;

  @Bean
  @Autowired
  public Retrofit retrofit(OkHttpClient client, Gson gson) {
    return new Retrofit.Builder()
      .baseUrl(apiBaseUrl)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build();
  }

  @Bean
  public Gson gson() {
    return new GsonBuilder().create();
  }
}
