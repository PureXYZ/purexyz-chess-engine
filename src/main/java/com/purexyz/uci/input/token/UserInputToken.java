package com.purexyz.uci.input.token;

import lombok.AllArgsConstructor;

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
