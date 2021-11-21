package com.purexyz.uci.input.engine;

import com.purexyz.engine.EngineState;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract engine call.
 * Implements Supplier for CompletableFuture.
 * Can be called sync or async.*/
@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();

  protected abstract EngineResult compute() throws Exception;

  @Override
  public final EngineResult get() {

    if (!shouldCallAsync()) {
      return catchCompute();
    }

    if (!EngineState.isReady()) {
      log.warn("Call before engine has given readyok");
      return EngineResult.emptyResult();
    }
    EngineState.setReady(false);
    EngineResult result = catchCompute();
    EngineState.setReady(true);
    return result;
  }

  private EngineResult catchCompute() {
    try {
      return compute();
    } catch (Exception e) {
      log.error("Compute exception", e);
      throw new RuntimeException(e);
    }
  }

  protected static void appendWithNewLine(StringBuilder builder, String line) {
    builder.append(line);
    builder.append(System.lineSeparator());
  }

  protected static String normalizeString(String line) {
    return line.strip().toLowerCase();
  }
}
