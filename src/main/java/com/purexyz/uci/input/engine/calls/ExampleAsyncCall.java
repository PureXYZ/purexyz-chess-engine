package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ExampleAsyncCall extends AbstractEngineCall {

  private final String value;

  @Override
  public boolean shouldCallAsync() {
    return true;
  }

  @Override
  public EngineResult compute() throws InterruptedException {
    log.info("Computing example async call with value: {}", value);
    Thread.sleep(1000);
    return new EngineResult(value);
  }
}
