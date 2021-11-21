package com.purexyz.uci.input.engine;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  protected static void appendWithNewLine(StringBuilder builder, String line) {
    builder.append(line);
    builder.append(System.lineSeparator());
  }

  protected static String normalizeString(String line) {
    return line.strip().toLowerCase();
  }

  public abstract boolean shouldCallAsync();

  protected abstract EngineResult compute();

  @Override
  public final EngineResult get() {
    return compute();
  }
}
