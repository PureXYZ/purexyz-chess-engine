package com.purexyz.engine.option;

import com.purexyz.engine.EngineState;
import java.util.function.Consumer;

/** The type Option calls. */
public class OptionCalls {

  private OptionCalls() {}

  /**
   * Enable async consumer.
   *
   * @return the consumer
   */
  public static Consumer<Option> enableAsync() {
    return (Option option) -> {
      EngineState.setEnableAsync(Boolean.valueOf(option.getCurrentValue()));
    };
  }
}
