package com.purexyz.uci.input.token;

public interface InputToken {

  String getValue();

  Type getType();

  enum Type {
    COMMAND,
    INPUT
  }
}
