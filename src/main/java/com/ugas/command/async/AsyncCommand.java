package com.ugas.command.async;

public class AsyncCommand<PARAMETER_TYPE, RESPONSE_TYPE> {

  public AsyncCommand<PARAMETER_TYPE, RESPONSE_TYPE> onSuccess(final SuccessCallback<RESPONSE_TYPE> callback) {
    return this;
  }

  public AsyncCommand onTimeout(final TimeoutCallback callback) {
    return this;
  }

  public AsyncCommand onError(final ErrorCallback callback) {
    return this;
  }

  public void send(final PARAMETER_TYPE parameter) {
  }
}
