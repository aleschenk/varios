package com.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import scala.reflect.internal.Trees;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RetroFitMain {

  static ApiClient apiClient = apiClient();

  static RetryTemplate retryTemplate = retryTemplate();

  public static class HttpClientErrorException extends RuntimeException {
    public HttpClientErrorException(String message) {
      super(message);
    }
  }

  public static class HttpServerErrorException extends RuntimeException {
    public HttpServerErrorException(String message) {
      super(message);
    }
  }

  public static class ErrorInterceptor implements  Interceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {
      Request request = chain.request();

      System.out.println("ERROR INTERCEPTOR: before request");
      Response response = chain.proceed(request);
      System.out.println("ERROR INTERCEPTOR: after request. response code: " + response.code());

      if(response.code() >= 500) {
        throw new HttpServerErrorException("Server code response was " + response.code());
      } else if (response.code() >= 400 && response.code() < 500) {
        throw new HttpClientErrorException("Client bad request");
      }
      return response;
    }
  }

  public static class RetryInterceptor implements Interceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {

      Request request = chain.request();

      Response response = retryTemplate.execute(context -> {

        System.out.println("RETRY INTERCEPTOR: before request. Retries: " + context.getRetryCount());
        Response chainResponse = chain.proceed(request);
        System.out.println("RETRY INTERCEPTOR: after request. response code: " + chainResponse.code());

          return chainResponse;
        }
      );

      return response;
    }
  }

  public static void main(final String[] args) throws IOException {
    retrofit2.Response<String> response = apiClient.hello().execute();
//    retrofit2.Response<String> response = apiClient.error().execute();
//    retrofit2.Response<String> response = apiClient.bad().execute();

    if (!response.isSuccessful()) {
      throw new RuntimeException("FALLO");
    }

    System.out.println(response.body());

  }

  private static ApiClient apiClient() {
    return retrofit().create(ApiClient.class);
  }

  private static Retrofit retrofit() {
    OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(new RetryInterceptor())
      .addInterceptor(new ErrorInterceptor())
      .connectTimeout(3, SECONDS)
      .readTimeout(3, SECONDS)
      .readTimeout(3, SECONDS)
      .build();

    return new Retrofit.Builder()
//      .baseUrl("http://localhost:4567/")
      .baseUrl("http://localhost:8080/")
      .addConverterFactory(ScalarsConverterFactory.create())
//      .addConverterFactory(JacksonConverterFactory.create())
      .client(client)
      .build();
  }

  private static RetryTemplate retryTemplate() {

    Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap();
    exceptions.put(HttpServerErrorException.class, true);
    exceptions.put(SocketTimeoutException.class, true);

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3, exceptions);

    ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
    exponentialBackOffPolicy.setInitialInterval(500);

    RetryTemplate template = new RetryTemplate();
    template.setRetryPolicy(retryPolicy);
    template.setBackOffPolicy(exponentialBackOffPolicy);

    return template;
  }

}
