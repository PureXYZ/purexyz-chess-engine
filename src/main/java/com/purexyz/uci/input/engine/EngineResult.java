package com.purexyz.uci.input.engine;

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
      System.out.println(result);
    }
  }
}
