package com.purexyz.engine;

import com.purexyz.engine.option.Option.SupportedOption;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/** The type Engine state. */
@Slf4j
public class EngineState {

  /** The constant enableAsync. */
  @Getter @Setter
  private static boolean enableAsync =
      Boolean.parseBoolean(SupportedOption.ENABLE_ASYNC.getDefaultValue());

  /** Instantiates a new Engine state. */
  private EngineState() {}

  /** Sets engine state. */
  public static void setupEngineState() {
    log.info("Setting up engine state");
    EngineOptions.getOptions().forEach(o -> o.getSetup().accept(o));
    log.info("Engine state setup completed");
  }
}
