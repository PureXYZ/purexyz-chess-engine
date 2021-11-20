package com.purexyz.uci.input.engine.calls;

import com.purexyz.engine.EngineState;
import com.purexyz.engine.option.EngineOptions;
import com.purexyz.engine.option.Option;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import java.util.Collection;
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

    EngineState.setupEngineState();

    StringBuilder builder = new StringBuilder();
    appendWithNewLine(builder, "id name purexyz-chess-engine");
    appendWithNewLine(builder, "id author PureXYZ");

    Collection<Option> options = EngineOptions.getOptions();
    if (options != null && !options.isEmpty()) {
      for (Option option : options) {
        appendWithNewLine(builder, option.toString());
      }
    }

    builder.append("uciok");

    return new EngineResult(builder.toString());
  }
}
