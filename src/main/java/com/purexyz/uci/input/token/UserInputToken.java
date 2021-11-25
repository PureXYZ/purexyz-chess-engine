package com.purexyz.uci.input.token;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class UserInputToken implements InputToken {

  private final String input;

  @Override
  public String getValue() {
    return input;
  }

  @Override
  public Type getType() {
    return Type.INPUT;
  }
}
