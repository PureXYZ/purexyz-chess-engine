package com.purexyz.uci.input.token;

/** The interface Input token. */
public interface InputToken {

  /**
   * Gets value.
   *
   * @return the value
   */
  String getValue();

  /**
   * Gets type.
   *
   * @return the type
   */
  Type getType();

  /** The enum Type. */
  enum Type {
    /** Command type. */
    COMMAND,
    /** Input type. */
    INPUT
  }
}
