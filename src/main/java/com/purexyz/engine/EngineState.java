package com.purexyz.engine;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EngineState {

  @Getter
  @Setter
  private static boolean enableAsync;

  private EngineState() {}

  public static void setupEngineState() {
    log.info("Setting up engine state");
    EngineOptions.getOptions().forEach(o -> o.getSetup().accept(o));
    log.info("Engine state setup completed");
  }
}
