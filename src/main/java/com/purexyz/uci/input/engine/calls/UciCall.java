package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

/** Uci command of uci. */
@Slf4j
public class UciCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult get() {
    log.info("Computing uci call");

    String result = "id name purexyz-chess-engine"
        + System.lineSeparator()
        + "id author PureXYZ"
        + System.lineSeparator()
        + "uciok";

    return new EngineResult(result);
  }
}
