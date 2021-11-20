package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalEngineException extends RuntimeException {

  public InternalEngineException(Exception e) {
    super(e);
    log.error("InternalEngineException thrown, Exception: {}", e, this);
    System.exit(1);
  }

  public InternalEngineException(String message) {
    super(message);
    log.error(message, this);
    System.exit(1);
  }

  public InternalEngineException() {
    log.error("InternalEngineException thrown", this);
    System.exit(1);
  }
}
