package com.purexyz.uci.input.token;

/** Base input token interface. */
public interface InputToken {

  /** Type can be uci command or user input. */
  enum Type {
    COMMAND,
    INPUT
  }

  String getValue();

  Type getType();
}
