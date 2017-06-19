package com.ugas.command.async;

import com.ugas.command.UgasError;

public interface ErrorCallback {
  void error(final UgasError error);
}
