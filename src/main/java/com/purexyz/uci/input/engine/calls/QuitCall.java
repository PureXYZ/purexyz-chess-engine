package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

/** Uci command of quit. */
@Slf4j
public class QuitCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() {
    log.info("Computing quit call");

    System.exit(0);

    return EngineResult.emptyResult();
  }
}
