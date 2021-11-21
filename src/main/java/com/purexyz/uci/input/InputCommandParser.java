package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.calls.DebugCall;
import com.purexyz.uci.input.engine.calls.GoCall;
import com.purexyz.uci.input.engine.calls.IsReadyCall;
import com.purexyz.uci.input.engine.calls.PonderHitCall;
import com.purexyz.uci.input.engine.calls.PositionCall;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.engine.calls.RegisterCall;
import com.purexyz.uci.input.engine.calls.SetOptionCall;
import com.purexyz.uci.input.engine.calls.StopCall;
import com.purexyz.uci.input.engine.calls.UciCall;
import com.purexyz.uci.input.engine.calls.UciNewGameCall;
import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.Optional;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Input command parser.
 */
@Slf4j
public class InputCommandParser {

  private InputCommandParser() {}

  /**
   * Gets engine call.
   *
   * @param inputTokens the input tokens
   * @return the engine call
   */
public static Optional<AbstractEngineCall> getEngineCall(Queue<InputToken> inputTokens) {

    log.info("Parsing tokens: {}", inputTokens);

    if (!validateInputTokens(inputTokens)) {
      return Optional.empty();
    }

    CommandInputToken command = (CommandInputToken) inputTokens.remove();
    log.info("Consuming token: {}", command);

    AbstractEngineCall result = switch (command) {
      case UCI -> uci();
      case DEBUG -> debug(inputTokens);
      case IS_READY -> isReady();
      case SET_OPTION -> setOption(inputTokens);
      case REGISTER -> register(inputTokens);
      case UCI_NEW_GAME -> uciNewGame();
      case POSITION -> position(inputTokens);
      case GO -> go(inputTokens);
      case STOP -> stop();
      case PONDER_HIT -> ponderHit();
      case QUIT -> quit();
      default -> null;
    };

    if (result == null) {
      log.warn("No engine call found");
      return Optional.empty();
    }

    return Optional.of(result);
  }

  private static boolean validateInputTokens(Queue<InputToken> inputTokens) {
    if (inputTokens == null || inputTokens.isEmpty()) {
      log.warn("Tokens are empty");
      return false;
    }

    if (inputTokens.peek().getType() != Type.COMMAND) {
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

  private static AbstractEngineCall debug(Queue<InputToken> rest) {
    log.info("Creating debug engine call");

    InputToken token = rest.remove();
    log.info("Consuming token: {}", token);

    if (token.getType() != Type.COMMAND) {
      return null;
    }

    CommandInputToken command = (CommandInputToken) token;
    if (command != CommandInputToken.DEBUG_ON && command != CommandInputToken.DEBUG_OFF) {
      return null;
    }

    return new DebugCall(command.getValue());
  }

  private static AbstractEngineCall isReady() {
    log.info("Creating isready engine call");
    return new IsReadyCall();
  }

  private static AbstractEngineCall setOption(Queue<InputToken> rest) {
    log.info("Creating setoption engine call");
    return new SetOptionCall();
  }

  private static AbstractEngineCall register(Queue<InputToken> rest) {
    log.info("Creating register engine call");
    return new RegisterCall();
  }

  private static AbstractEngineCall uciNewGame() {
    log.info("Creating ucinewgame engine call");
    return new UciNewGameCall();
  }

  private static AbstractEngineCall position(Queue<InputToken> rest) {
    log.info("Creating position engine call");
    return new PositionCall();
  }

  private static AbstractEngineCall go(Queue<InputToken> rest) {
    log.info("Creating go engine call");
    return new GoCall();
  }

  private static AbstractEngineCall stop() {
    log.info("Creating stop engine call");
    return new StopCall();
  }

  private static AbstractEngineCall ponderHit() {
    log.info("Creating ponderhit engine call");
    return new PonderHitCall();
  }
}
