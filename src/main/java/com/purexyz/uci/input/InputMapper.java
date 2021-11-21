package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

/** The type Input mapper. */
@Slf4j
public class InputMapper {

  /** The constant instance. */
  private static InputMapper instance;

  /** Instantiates a new Input mapper. */
  private InputMapper() {}

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static InputMapper getInstance() {
    if (instance == null) {
      instance = new InputMapper();
    }

    return instance;
  }

  /**
   * Map optional.
   *
   * @param tokens the tokens
   * @return the optional
   */
  public Optional<AbstractEngineCall> map(Queue<InputToken> tokens) {
    tokens = normalize(tokens);

    if (tokens.isEmpty()) {
      log.warn("No engine call found");
      return Optional.empty();
    }

    return InputCommandParser.getEngineCall(tokens);
  }

  /**
   * Normalize queue.
   *
   * @param tokens the tokens
   * @return the queue
   */
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
