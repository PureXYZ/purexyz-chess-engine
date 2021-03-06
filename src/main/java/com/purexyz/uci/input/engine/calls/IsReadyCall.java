package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class IsReadyCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() {
    log.info("Computing isready call");
    return new EngineResult("readyok");
  }
}
