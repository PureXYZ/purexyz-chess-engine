package com.purexyz.engine.option;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Option<T> {

  private String name;
  private Type type;
  private T defaultValue;
  private Integer min;
  private Integer max;
  private List<T> vars;
  private T currentValue;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("option name ");
    builder.append(name);

    builder.append(" type ");
    builder.append(type.toString());

    builder.append(" default ");
    builder.append(defaultValue.toString());

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
      for (T var : vars) {
        builder.append(" ");
        builder.append(var.toString());
      }
    }

    return builder.toString();
  }

  public enum Type {
    CHECK,
    SPIN,
    COMBO,
    BUTTON,
    STRING;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }
}
