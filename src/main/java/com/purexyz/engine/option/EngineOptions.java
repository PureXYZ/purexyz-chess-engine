package com.purexyz.engine.option;

import com.purexyz.engine.option.Option.SupportedOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EngineOptions {

  private EngineOptions() {}

  private static final Map<SupportedOption, Option> optionMap = new HashMap<>();

  static {
    for (SupportedOption supportedOption : SupportedOption.values()) {
      optionMap.put(supportedOption, Option.buildSupportedOption(supportedOption));
    }
  }

  public static Collection<Option> getOptions() {
    return optionMap.values();
  }
}
