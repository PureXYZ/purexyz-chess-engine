package com.purexyz.engine.option;

import com.purexyz.engine.EngineState;
import java.util.function.Consumer;

public class OptionCalls {

  private OptionCalls() {}

  public static Consumer<Option> enableAsync() {
    return (Option option) -> EngineState.setEnableAsync(Boolean.parseBoolean(option.getCurrentValue()));
  }
}
