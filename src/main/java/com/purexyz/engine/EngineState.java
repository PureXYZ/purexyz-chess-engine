package com.purexyz.engine;

import com.purexyz.engine.option.EngineOptions;
import com.purexyz.engine.option.Option.SupportedOption;
import lombok.Getter;
import lombok.Setter;

public class EngineState {

  private EngineState() {}

  @Getter
  @Setter
  private static volatile boolean isReady = true;

  @Getter
  @Setter
  private static boolean enableAsync = Boolean.parseBoolean(SupportedOption.ENABLE_ASYNC.getDefaultValue());

  public static void setupEngineState() {
    EngineOptions.getOptions().forEach(o -> o.getSetup().accept(o));
  }
}
