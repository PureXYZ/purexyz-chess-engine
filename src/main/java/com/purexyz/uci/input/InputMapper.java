package com.purexyz.uci.input;

import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
public class InputMapper {

  private static InputMapper INSTANCE;

  private InputMapper() {}

  public static InputMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputMapper();
    }

    return INSTANCE;
  }

  public Optional<Callable<String>> map(List<InputToken> tokens) {

    tokens = normalize(tokens);

    if (tokens.isEmpty()) {
      return Optional.empty();
    }

    InputToken firstToken = tokens.get(0);
    if (Type.COMMAND != firstToken.getType()) {
      return Optional.empty();
    }

    // TODO get Callable engine call

    return Optional.empty();
  }

  private List<InputToken> normalize(List<InputToken> tokens) {

    if (tokens == null || tokens.isEmpty()) {
      return List.of();
    }

    while (!tokens.isEmpty()) {
      InputToken currentToken = tokens.get(0);

      if (Type.COMMAND != currentToken.getType()) {
        tokens.remove(currentToken);
      }
    }

    return tokens;
  }
}
