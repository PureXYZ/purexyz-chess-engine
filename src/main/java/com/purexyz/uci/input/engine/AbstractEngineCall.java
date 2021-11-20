package com.purexyz.uci.input.engine;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract engine call.
 * Implements Supplier for CompletableFuture.
 * Can be called sync or async.*/
@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();

  public abstract EngineResult compute();

  @Override
  public EngineResult get() {
    try {
      log.info("Computing engine result");
      return compute();
    } catch (Exception e) {
      log.error("Exception thrown on engine compute", e);
      return EngineResult.emptyResult();
    }
  }
}
