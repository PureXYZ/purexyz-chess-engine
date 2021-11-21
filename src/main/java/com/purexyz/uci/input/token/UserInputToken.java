package com.purexyz.uci.input.token;

import lombok.AllArgsConstructor;

/** The type User input token. */
@AllArgsConstructor
public class UserInputToken implements InputToken {

  /** The Input. */
  String input;

  /**
   * Gets value.
   *
   * @return the value
   */
  @Override
  public String getValue() {
    return input;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  @Override
  public Type getType() {
    return Type.INPUT;
  }
}
