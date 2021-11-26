package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriticalEngineException extends RuntimeException {

  public CriticalEngineException(Throwable e) {
    super(e);
    log.error("CriticalEngineException thrown, Throwable: {}", e, this);
    exit();
  }

  public CriticalEngineException(String message) {
    super(message);
    log.error(message, this);
    exit();
  }

  public CriticalEngineException() {
    log.error("CriticalEngineException thrown", this);
    exit();
  }

  private void exit() {
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }
}
