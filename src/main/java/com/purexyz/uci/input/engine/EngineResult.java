package com.purexyz.uci.input.engine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record EngineResult(String result) {

  private static final EngineResult emptyResult = new EngineResult(null);

  public static EngineResult emptyResult() {
    return emptyResult;
  }

  public boolean isEmpty() {
    return result == null || result.isBlank();
  }

  public void printResult() {
    if (!isEmpty()) {
      log.info("Printing result");
      System.out.println(result);
    } else {
      log.warn("Engine result is empty");
    }
  }
}
