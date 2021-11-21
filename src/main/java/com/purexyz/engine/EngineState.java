package com.purexyz.engine;

import com.purexyz.engine.option.EngineOptions;
import com.purexyz.engine.option.Option.SupportedOption;
import lombok.Getter;
import lombok.Setter;

/** The type Engine state. */
public class EngineState {

  /** The constant isReady. */
  @Getter @Setter private static volatile boolean isReady = true;

  /** The constant enableAsync. */
  @Getter @Setter
  private static boolean enableAsync =
      Boolean.parseBoolean(SupportedOption.ENABLE_ASYNC.getDefaultValue());

  /** Instantiates a new Engine state. */
  private EngineState() {}

  /** Sets engine state. */
  public static void setupEngineState() {
    EngineOptions.getOptions().forEach(o -> o.getSetup().accept(o));
  }
}
