package com.purexyz.uci.input.engine.calls;

import com.purexyz.exceptions.InternalEngineException;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExampleAsyncCall extends AbstractEngineCall {

  private final String value;

  @Override
  public boolean shouldCallAsync() {
    return true;
  }

  @Override
  public EngineResult compute() throws InterruptedException {
    Thread.sleep(10000);
    return new EngineResult(value);
  }
}
