package com.purexyz.engine.option;

import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Option {

  private String name;
  private Type type;
  private String defaultValue;
  private Integer min;
  private Integer max;
  private List<String> vars;
  private String currentValue;
  private Consumer<Option> setup;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("option name ");
    builder.append(name);

    builder.append(" type ");
    builder.append(type.getValue());

    builder.append(" default ");
    builder.append(defaultValue);

    if (min != null) {
      builder.append(" min ");
      builder.append(min);
    }

    if (max != null) {
      builder.append(" max ");
      builder.append(max);
    }

    if (vars != null && !vars.isEmpty()) {
      builder.append(" var");
      for (String var : vars) {
        builder.append(" ");
        builder.append(var);
      }
    }

    return builder.toString();
  }

  public static Option buildSupportedOption(SupportedOption supportedOption) {
    return builder()
        .name(supportedOption.getName())
        .type(supportedOption.getType())
        .currentValue(supportedOption.getDefaultValue())
        .defaultValue(supportedOption.getDefaultValue())
        .min(supportedOption.getMin())
        .max(supportedOption.getMax())
        .vars(supportedOption.getVars())
        .setup(supportedOption.getSetup())
        .build();
  }

  @Getter
  @AllArgsConstructor
  public enum Type {
    CHECK("check"),
    SPIN("spin"),
    COMBO("combo"),
    BUTTON("button"),
    STRING("string");

    private String value;
  }

  @Getter
  @AllArgsConstructor
  public enum SupportedOption {
    ENABLE_ASYNC("Enable_Async", Type.CHECK, "true", null, null, null,  OptionCalls.enableAsync());

    private String name;
    private Type type;
    private String defaultValue;
    private Integer min;
    private Integer max;
    private List<String> vars;
    private Consumer<Option> setup;
  }
}
