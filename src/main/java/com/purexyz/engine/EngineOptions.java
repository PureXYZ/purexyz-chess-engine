package com.purexyz.engine;

import com.purexyz.engine.EngineState;
import com.purexyz.engine.option.Option;
import com.purexyz.engine.option.Option.SupportedOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** The type Engine options. */
@Slf4j
public class EngineOptions {

  /** The constant optionMap. */
  private static final Map<SupportedOption, Option> optionMap = new HashMap<>();

  static {
    for (SupportedOption supportedOption : SupportedOption.values()) {
      optionMap.put(supportedOption, Option.buildSupportedOption(supportedOption));
    }
    EngineState.setupEngineState();
  }

  /** Instantiates a new Engine options. */
  private EngineOptions() {}

  /**
   * Gets options.
   *
   * @return the options
   */
  public static Collection<Option> getOptions() {
    return optionMap.values();
  }
}
