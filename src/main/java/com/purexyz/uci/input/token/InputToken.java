package com.purexyz.uci.input.token;

/** The interface Input token. */
public interface InputToken {

  /** The enum Type. */
  enum Type {
    /** Command type. */
    COMMAND,
    /** Input type. */
    INPUT
  }

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
}
