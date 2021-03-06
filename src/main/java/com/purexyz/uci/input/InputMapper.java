package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputMapper {

  private static InputMapper instance;

  private InputMapper() {}

  public static InputMapper getInstance() {
    if (instance == null) {
      instance = new InputMapper();
    }

    return instance;
  }

  public Optional<AbstractEngineCall> map(Queue<InputToken> tokens) {
    tokens = normalize(tokens);

    if (tokens.isEmpty()) {
      log.warn("No engine call found");
      return Optional.empty();
    }

    return InputCommandParser.getEngineCall(tokens);
  }

  private Queue<InputToken> normalize(Queue<InputToken> tokens) {
    if (tokens == null || tokens.isEmpty()) {
      log.info("Token list is empty");
      return new ArrayDeque<>();
    }

    while (!tokens.isEmpty()) {
      InputToken currentToken = tokens.peek();

      if (Type.COMMAND == currentToken.getType()) {
        log.info("Reached command token: {}", currentToken);
        break;
      } else {
        log.warn("Removing unexpected user input token");
        tokens.remove();
        log.info("Consuming token: {}", currentToken);
      }
    }

    return tokens;
  }
}
