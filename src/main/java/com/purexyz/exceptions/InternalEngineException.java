package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalEngineException extends RuntimeException {

  public InternalEngineException(Throwable e) {
    super(e);
    log.error("InternalEngineException thrown, Throwable: {}", e, this);
    exit();
  }

  public InternalEngineException(String message) {
    super(message);
    log.error(message, this);
    exit();
  }

  public InternalEngineException() {
    log.error("InternalEngineException thrown", this);
    exit();
  }

  private void exit() {
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }
}
