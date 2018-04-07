package com.reactor;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ReactorSandbox {

  public static void main(String[] args) {
    new ReactorSandbox().init();
  }

  RestHighLevelClient restHighLevelClient() {
    return new RestHighLevelClient(
      RestClient
        .builder(HttpHost.create(""))
        .setRequestConfigCallback(config -> config
          .setConnectTimeout(5000)
          .setConnectionRequestTimeout(5000)
          .setSocketTimeout(5000)
        )
        .setMaxRetryTimeoutMillis(5000));
  }

  public void init() {
    RestHighLevelClient client = restHighLevelClient();

  }


}
