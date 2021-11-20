package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/** Maps input tokens to AbstractEngineCall. */
@Slf4j
public class InputMapper {

  private static InputMapper instance;

  private InputMapper() {}

  /**
   * Singleton.
   *
   * @return Get InputMapper instance. */
  public static InputMapper getInstance() {
    if (instance == null) {
      instance = new InputMapper();
    }

    return instance;
  }

  /**
   * Maps input tokens to engine call.
   *
   * @param tokens from input.
   * @return AbstractEngineCall if successfully maps input to command.
   */
  public Optional<AbstractEngineCall> map(List<InputToken> tokens) {
    tokens = normalize(tokens);

    if (tokens.isEmpty()) {
      log.info("No engine call found");
      return Optional.empty();
    }

    return InputCommandParser.getEngineCall(tokens);
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
