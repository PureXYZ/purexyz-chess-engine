package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception thrown by chess engine.
 * Unrecoverable errors must exit. */
@Slf4j
public class InternalEngineException extends RuntimeException {

  /**
   * InternalEngineException.
   *
   * @param e Exception.*/
  public InternalEngineException(Throwable e) {
    super(e);
    log.error("InternalEngineException thrown, Throwable: {}", e, this);
    exit();
  }

  /**
   * InternalEngineException.
   *
   * @param message String message. */
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
