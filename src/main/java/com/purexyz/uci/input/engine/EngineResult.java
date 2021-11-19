package com.purexyz.uci.input.engine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EngineResult {

  private final String result;
  private static final EngineResult emptyResult = new EngineResult(null);

  public boolean isEmpty() {
    return (result == null || result.isBlank());
  }

  public static EngineResult emptyResult() {
    return emptyResult;
  }

  public void printResult() {
    if (!isEmpty()) {
      System.out.println(result);
    }
  }
}
