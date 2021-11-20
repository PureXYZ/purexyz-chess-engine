package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.engine.calls.UciCall;
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
    log.info("Consuming token: {}", command);

    if (command == CommandInputToken.QUIT) {
      return Optional.of(quit());
    }

    AbstractEngineCall result = switch(command) {
      case UCI -> uci();
      case DEBUG -> null;
      case IS_READY -> null;
      case SET_OPTION -> null;
      case REGISTER -> null;
      case UCI_NEW_GAME -> null;
      case POSITION -> null;
      case GO -> null;
      case STOP -> null;
      case PONDER_HIT -> null;
      case QUIT -> quit();
      default -> null;
    };

    if (result == null) {
      log.warn("No engine call found");
      return Optional.empty();
    }

    return Optional.of(result);
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

  private static AbstractEngineCall quit() {
    log.info("Creating quit engine call");
    return new QuitCall();
  }

  private static AbstractEngineCall uci() {
    log.info("Creating uci engine call");
    return new UciCall();
  }
}
