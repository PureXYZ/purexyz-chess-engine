package com.purexyz.exceptions;

import lombok.extern.slf4j.Slf4j;

/** The type Internal engine exception. */
@Slf4j
public class InternalEngineException extends RuntimeException {

  /**
   * Instantiates a new Internal engine exception.
   *
   * @param e the e
   */
  public InternalEngineException(Throwable e) {
    super(e);
    log.error("InternalEngineException thrown, Throwable: {}", e, this);
    exit();
  }

  /**
   * Instantiates a new Internal engine exception.
   *
   * @param message the message
   */
  public InternalEngineException(String message) {
    super(message);
    log.error(message, this);
    exit();
  }

  /** Instantiates a new Internal engine exception. */
  public InternalEngineException() {
    log.error("InternalEngineException thrown", this);
    exit();
  }

  /** Exit. */
  private void exit() {
    log.error("Unrecoverable error, exiting");
    System.exit(1);
  }
}
