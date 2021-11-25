package com.purexyz.uci.input.engine;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  protected static void appendWithNewLine(StringBuilder builder, String line) {
    builder.append(line);
    builder.append(System.lineSeparator());
  }

  public abstract boolean shouldCallAsync();

  protected abstract EngineResult compute();

  @Override
  public abstract String toString();

  @Override
  public final EngineResult get() {
    return compute();
  }
}
