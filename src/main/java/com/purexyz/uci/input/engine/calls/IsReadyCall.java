package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

/** The type Is ready call. */
@Slf4j
public class IsReadyCall extends AbstractEngineCall {

  /**
   * Should call async boolean.
   *
   * @return the boolean
   */
  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  /**
   * Compute engine result.
   *
   * @return the engine result
   */
  @Override
  public EngineResult compute() {
    log.info("Computing isready call");
    return new EngineResult("readyok");
  }
}
