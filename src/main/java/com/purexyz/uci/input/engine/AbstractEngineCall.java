package com.purexyz.uci.input.engine;

import com.purexyz.exceptions.InternalEngineException;
import java.util.function.Supplier;
import lombok.Getter;

@Getter
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();

  public abstract EngineResult compute() throws Exception;

  @Override
  public EngineResult get() {
    try {
      EngineResult engineResult = compute();
      return engineResult;
    } catch (Exception e) {
      throw new InternalEngineException(e);
    }
  }
}
