package com.purexyz.uci.input.token;

import lombok.AllArgsConstructor;

/** Input token for user input. */
@AllArgsConstructor
public class UserInputToken implements InputToken {

  String input;

  @Override
  public String getValue() {
    return input;
  }

  @Override
  public Type getType() {
    return Type.INPUT;
  }
}
