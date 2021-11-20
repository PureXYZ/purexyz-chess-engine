package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UciCall extends AbstractEngineCall {

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() throws Exception {
    log.info("Computing uci call");

    var builder = new StringBuilder();
    builder.append("id name purexyz-chess-engine");
    builder.append(System.lineSeparator());
    builder.append("id author PureXYZ");
    builder.append(System.lineSeparator());
    builder.append("uciok");

    return new EngineResult(builder.toString());
  }
}
