package com.purexyz.uci.input.engine;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Engine result.
 */
@Slf4j
public record EngineResult(String result) {

  private static final EngineResult emptyResult = new EngineResult(null);

  /**
   * Empty result engine result.
   *
   * @return the engine result
   */
public static EngineResult emptyResult() {
    return emptyResult;
  }

  /**
   * Is empty boolean.
   *
   * @return the boolean
   */
public boolean isEmpty() {
    return result == null || result.isBlank();
  }

  /**
   * Print result.
   */
public void printResult() {
    if (!isEmpty()) {
      log.info("Printing result");
      System.out.println(result);
    } else {
      log.warn("Engine result is empty");
    }
  }
}
