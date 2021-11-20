package com.purexyz.engine.option;

import com.purexyz.engine.option.Option.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineOptions {

  private EngineOptions() {}

  private static final Map<String, Option> optionMap = new HashMap<>();

  static {
    addOption(buildLoggingOption());
  }

  public static Collection<Option> getOptions() {
    return optionMap.values();
  }

  public static void addOption(Option option) {
    option.setCurrentValue(option.getDefaultValue());
    optionMap.put(option.getName(), option);
  }

  private static Option buildLoggingOption() {
    return Option.builder()
        .name("Logging")
        .type(Type.COMBO)
        .defaultValue("off")
        .vars(List.of("off", "trace", "debug", "info", "warn", "error"))
        .build();
  }
}
