package com.purexyz.exceptions;

import java.util.concurrent.CompletionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalEngineException extends CompletionException {

  public InternalEngineException(Exception e) {
    super(e);
    log.error("InternalEngineException thrown, Exception: {}", e, this);
  }

  public InternalEngineException(String message) {
    super(message);
    log.error(message, this);
  }

  public InternalEngineException() {
    log.error("InternalEngineException thrown", this);
  }
}
