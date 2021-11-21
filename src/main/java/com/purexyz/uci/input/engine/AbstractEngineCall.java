package com.purexyz.uci.input.engine;

import com.purexyz.engine.EngineState;
import com.purexyz.exceptions.InternalEngineException;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract engine call.
 * Implements Supplier for CompletableFuture.
 * Can be called sync or async.*/
@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();

  public abstract EngineResult compute();

  @Override
  public final EngineResult get() {

    if (shouldCallAsync()) {
      if (!EngineState.isReady()) {
        throw new InternalEngineException("Call before engine has given readyok");
      }
      EngineState.setReady(false);
      EngineResult result = compute();
      EngineState.setReady(true);
      return result;
    }

    return compute();
  }

  protected static void appendWithNewLine(StringBuilder builder, String line) {
    builder.append(line);
    builder.append(System.lineSeparator());
  }
}
