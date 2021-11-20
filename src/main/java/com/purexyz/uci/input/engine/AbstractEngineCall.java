package com.purexyz.uci.input.engine;

import com.purexyz.exceptions.InternalEngineException;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();

  public abstract EngineResult compute() throws Exception;

  @Override
  public EngineResult get() {
    try {
      return compute();
    } catch (Exception e) {
      log.error("Exception thrown on engine compute", e);
      return EngineResult.emptyResult();
    }
  }
}
