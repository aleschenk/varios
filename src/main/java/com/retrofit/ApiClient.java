package com.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

  @GET("hello")
  Call<String> hello();

  @GET("error")
  Call<String> error();

  @GET("bad")
  Call<String> bad();


}
