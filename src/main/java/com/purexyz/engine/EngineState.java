package com.purexyz.engine;

import com.purexyz.engine.option.Option.SupportedOption;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EngineState {

  @Getter
  @Setter
  private static boolean enableAsync = Boolean.parseBoolean(SupportedOption.ENABLE_ASYNC.getDefaultValue());

  private EngineState() {}

  public static void setupEngineState() {
    log.info("Setting up engine state");
    EngineOptions.getOptions().forEach(o -> o.getSetup().accept(o));
    log.info("Engine state setup completed");
  }
}
