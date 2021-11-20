package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalEngineException extends RuntimeException {

  public InternalEngineException(Exception e) {
    super(e);
    log.error("InternalEngineException thrown, Exception: {}", e, this);
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }

  public InternalEngineException(String message) {
    super(message);
    log.error(message, this);
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }

  public InternalEngineException() {
    log.error("InternalEngineException thrown", this);
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }
}
