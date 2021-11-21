package com.purexyz.uci;

import com.purexyz.engine.EngineState;
import com.purexyz.exceptions.InternalEngineException;
import com.purexyz.uci.input.InputMapper;
import com.purexyz.uci.input.InputTokenizer;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import com.purexyz.uci.input.token.InputToken;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

/** The type Input handler. */
@Slf4j
public class InputHandler {

  /** The constant inputTokenizer. */
  private static final InputTokenizer inputTokenizer = InputTokenizer.getInstance();

  /** The constant inputMapper. */
  private static final InputMapper inputMapper = InputMapper.getInstance();

  /** The constant instance. */
  private static InputHandler instance;

  /** Instantiates a new Input handler. */
  private InputHandler() {}

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandler();
    }

    return instance;
  }

  /**
   * Handle input.
   *
   * @param input the input
   */
  public void handleInput(String input) {

    log.info("Handling input: {}", input);

    Queue<InputToken> tokens = inputTokenizer.tokenize(input);
    Optional<AbstractEngineCall> engineCallOpt = inputMapper.map(tokens);

    if (engineCallOpt.isEmpty()) {
      log.warn("No command found for input");
      return;
    }

    AbstractEngineCall engineCall = engineCallOpt.get();

    if (EngineState.isEnableAsync() && engineCall.shouldCallAsync()) {
      log.info("Calling async call: {}", engineCall);
      callAsync(engineCall);
    } else {
      log.info("Calling synchronous call: {}", engineCall);
      callSync(engineCall);
    }
  }

  /**
   * Call async.
   *
   * @param engineCall the engine call
   */
  private void callAsync(AbstractEngineCall engineCall) {
    CompletableFuture.supplyAsync(engineCall)
        .whenComplete(
            (result, exception) -> {
              if (exception != null) {
                throw new InternalEngineException(exception);
              } else {
                result.printResult();
              }
            });
  }

  /**
   * Call sync.
   *
   * @param engineCall the engine call
   */
  private void callSync(AbstractEngineCall engineCall) {
    EngineResult engineResult = engineCall.get();
    engineResult.printResult();
  }
}
