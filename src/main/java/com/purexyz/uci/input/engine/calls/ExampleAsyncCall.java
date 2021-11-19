package com.purexyz.uci.input.engine.calls;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ExampleAsyncCall extends AbstractEngineCall {

  private final String value;

  @Override
  public boolean shouldCallAsync() {
    return true;
  }

  @SneakyThrows
  @Override
  public EngineResult get() {
    Thread.sleep(10000);
    return new EngineResult(value);
  }
}
