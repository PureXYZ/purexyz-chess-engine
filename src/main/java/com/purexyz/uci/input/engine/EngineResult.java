package com.purexyz.uci.input.engine;

import lombok.extern.slf4j.Slf4j;

/** Record representing string output of engine. */
@Slf4j
public record EngineResult(String result) {

  private static final EngineResult emptyResult = new EngineResult(null);

  public static EngineResult emptyResult() {
    return emptyResult;
  }

  public boolean isEmpty() {
    return result == null || result.isBlank();
  }

  /**
  * Print the engine output.
  */
  public void printResult() {
    if (!isEmpty()) {
      log.info("Printing result: {}", result);
      System.out.println(result);
    } else {
      log.warn("Engine result is empty");
    }
  }
}
