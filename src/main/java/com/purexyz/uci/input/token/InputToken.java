package com.purexyz.uci.input.token;

public interface InputToken {

  enum Type {
    COMMAND,
    INPUT
  }

  String getValue();

  Type getType();
}
