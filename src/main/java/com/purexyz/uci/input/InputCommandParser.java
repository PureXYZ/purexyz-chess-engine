package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputCommandParser {

  private static InputCommandParser INSTANCE;

  private InputCommandParser() {}

  public static InputCommandParser getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputCommandParser();
    }

    return INSTANCE;
  }

  public static Optional<AbstractEngineCall> getEngineCall(List<InputToken> inputTokens) {

    log.info("Parsing tokens: {}", inputTokens);

    if (!validateInputTokens(inputTokens)) {
      return Optional.empty();
    }

    CommandInputToken command = (CommandInputToken) inputTokens.get(0);
    inputTokens.remove(0);
    log.info("Found command: {}", command);

    if (command == CommandInputToken.QUIT) {
      quit(inputTokens);
    }

    log.warn("No engine call found");
    return Optional.empty();
  }

  private static boolean validateInputTokens(List<InputToken> inputTokens) {
    if (inputTokens == null || inputTokens.isEmpty()) {
      log.warn("Tokens are empty");
      return false;
    }

    if (inputTokens.get(0).getType() != Type.COMMAND) {
      log.warn("First token must be command");
      return false;
    }

    return true;
  }

  private static AbstractEngineCall quit(List<InputToken> restInputTokens) {
    log.info("Creating quit engine call");
    return new QuitCall();
  }
}
