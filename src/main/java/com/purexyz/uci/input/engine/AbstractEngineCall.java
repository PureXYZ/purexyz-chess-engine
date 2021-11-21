package com.purexyz.uci.input.engine;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

/** The type Abstract engine call. */
@Slf4j
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  /**
   * Append with new line.
   *
   * @param builder the builder
   * @param line the line
   */
  protected static void appendWithNewLine(StringBuilder builder, String line) {
    builder.append(line);
    builder.append(System.lineSeparator());
  }

  /**
   * Normalize string string.
   *
   * @param line the line
   * @return the string
   */
  protected static String normalizeString(String line) {
    return line.strip().toLowerCase();
  }

  /**
   * Should call async boolean.
   *
   * @return the boolean
   */
  public abstract boolean shouldCallAsync();

  /**
   * Compute engine result.
   *
   * @return the engine result
   */
  protected abstract EngineResult compute();

  /**
   * Get engine result.
   *
   * @return the engine result
   */
  @Override
  public final EngineResult get() {
    return compute();
  }
}
