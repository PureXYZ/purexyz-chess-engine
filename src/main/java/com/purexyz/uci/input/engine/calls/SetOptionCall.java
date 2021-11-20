package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetOptionCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult get() {
    log.info("Computing setoption call");

    return EngineResult.emptyResult();
  }
}
