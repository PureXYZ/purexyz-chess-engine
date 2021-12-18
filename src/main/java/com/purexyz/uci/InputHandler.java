package com.purexyz.uci;

import com.purexyz.engine.EngineState;
import com.purexyz.exceptions.CriticalEngineException;
import com.purexyz.uci.input.InputMapper;
import com.purexyz.uci.input.InputTokenizer;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.token.InputToken;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputHandler {

  private static final InputTokenizer inputTokenizer = InputTokenizer.getInstance();

  private static final InputMapper inputMapper = InputMapper.getInstance();

  private static InputHandler instance;

  private InputHandler() {}

  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandler();
    }

    return instance;
  }

  public boolean handleInput(String input) {

    log.info("Handling input: {}", input);

    Queue<InputToken> tokens = inputTokenizer.tokenize(input);
    Optional<AbstractEngineCall> engineCallOpt = inputMapper.map(tokens);

    if (engineCallOpt.isEmpty()) {
      log.warn("No command found for input");
      return false;
    }

    AbstractEngineCall engineCall = engineCallOpt.get();

    if (engineCall instanceof QuitCall) {
      return true;
    }

    if (EngineState.isEnableAsync() && engineCall.shouldCallAsync()) {
      log.info("Calling async call: {}", engineCall);
      callAsync(engineCall);
    } else {
      log.info("Calling synchronous call: {}", engineCall);
      callSync(engineCall);
    }

    return false;
  }

  private void callAsync(AbstractEngineCall engineCall) {
    CompletableFuture.supplyAsync(engineCall)
        .whenComplete(
            (result, exception) -> {
              if (exception != null) {
                throw new CriticalEngineException(exception);
              } else {
                result.printResult();
              }
            });
  }

  private void callSync(AbstractEngineCall engineCall) {
    EngineResult engineResult = engineCall.get();
    engineResult.printResult();
  }
}
