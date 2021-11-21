package com.purexyz.uci.input.engine.calls;

import com.purexyz.engine.EngineState;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IsReadyCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() {
    log.info("Computing isready call");

    if (EngineState.isReady()) {
      return new EngineResult("readyok");
    } else {
      return new EngineResult("not ready");
    }
  }
}
