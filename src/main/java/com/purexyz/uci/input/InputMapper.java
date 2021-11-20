package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.calls.ExampleAsyncCall;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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

  public Optional<AbstractEngineCall> map(List<InputToken> tokens) {
    tokens = normalize(tokens);

    if (tokens.isEmpty()) {
      log.info("No engine call found");
      return Optional.empty();
    }

    // TODO temporarily handle quit
    if (tokens.get(0) == CommandInputToken.QUIT) {
      return Optional.of(new QuitCall());
    }

    // TODO get Callable engine call
    return Optional.of(new ExampleAsyncCall(tokens.get(0).getValue()));
  }

  private List<InputToken> normalize(List<InputToken> tokens) {
    if (tokens == null || tokens.isEmpty()) {
      log.info("Token list is empty");
      return List.of();
    }

    while (!tokens.isEmpty()) {
      InputToken currentToken = tokens.get(0);

      if (Type.COMMAND == currentToken.getType()) {
        log.info("Reached command token: {}", currentToken);
        break;
      } else {
        log.info("Removing unexpected user input token: {}", currentToken);
        tokens.remove(currentToken);
      }
    }

    return tokens;
  }
}
