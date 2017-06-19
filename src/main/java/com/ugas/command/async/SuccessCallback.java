package com.ugas.command.async;

public interface SuccessCallback<PARAMETER_TYPE> {

  void success(final PARAMETER_TYPE response);

}
