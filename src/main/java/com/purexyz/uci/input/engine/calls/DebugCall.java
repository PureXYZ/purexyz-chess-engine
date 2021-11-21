package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.impl.SimpleLogger;
import org.slf4j.impl.SimpleLoggerConfiguration;

@Slf4j
@AllArgsConstructor
public class DebugCall extends AbstractEngineCall {

  private String value;

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() {
    log.info("Computing debug call");

    if (value == null) {
      return EngineResult.emptyResult();
    }

    value = normalizeString(value);

    if (value.equals("on")) {
      // TODO
      return EngineResult.emptyResult();
    } else if (value.equals("off")) {
      // TODO
      return EngineResult.emptyResult();
    } else {
      return EngineResult.emptyResult();
    }
  }
}
