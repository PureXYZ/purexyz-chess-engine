package com.purexyz.uci;

import com.purexyz.exceptions.InternalEngineException;
import com.purexyz.uci.input.InputMapper;
import com.purexyz.uci.input.InputTokenizer;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import com.purexyz.uci.input.token.InputToken;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

/** Handles string input from stdin. */
@Slf4j
public class InputHandler {

  private static InputHandler instance;

  private static final InputTokenizer inputTokenizer = InputTokenizer.getInstance();
  private static final InputMapper inputMapper = InputMapper.getInstance();

  private InputHandler() {}

  /**
   * Singleton.
   *
   * @return Get instance. */
  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandler();
    }

    return instance;
  }

  /**
   * Handles input from stdin.
   * Makes call to engine and prints result.
   *
   * @param input User input from stdin. */
  public void handleInput(String input) {

    log.info("Handling input: {}", input);

    List<InputToken> tokens = inputTokenizer.tokenize(input);
    Optional<AbstractEngineCall> engineCallOpt = inputMapper.map(tokens);

    if (engineCallOpt.isEmpty()) {
      log.info("No command found for input");
      return;
    }

    AbstractEngineCall engineCall = engineCallOpt.get();

    if (engineCall.shouldCallAsync()) {
      log.info("Calling async call: {}", engineCall);
      callAsync(engineCall);
    } else {
      log.info("Calling synchronous call: {}", engineCall);
      callSync(engineCall);
    }
  }

  private void callAsync(AbstractEngineCall engineCall) {
    CompletableFuture.supplyAsync(engineCall)
        .thenAccept(EngineResult::printResult)
        .exceptionally(
            t -> {
              throw new InternalEngineException(t);
            });
  }

  private void callSync(AbstractEngineCall engineCall) {
    EngineResult engineResult = engineCall.get();
    engineResult.printResult();
  }
}
