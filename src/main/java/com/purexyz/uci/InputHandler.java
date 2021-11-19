package com.purexyz.uci;

import com.purexyz.uci.input.InputMapper;
import com.purexyz.uci.input.InputTokenizer;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import com.purexyz.uci.input.token.InputToken;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class InputHandler {

  private static InputHandler INSTANCE;

  private static final InputTokenizer inputTokenizer = InputTokenizer.getInstance();
  private static final InputMapper inputMapper = InputMapper.getInstance();

  private InputHandler() {}

  public static InputHandler getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputHandler();
    }

    return INSTANCE;
  }

  public void handleInput(String input) {
    List<InputToken> tokens = inputTokenizer.tokenize(input);
    Optional<AbstractEngineCall> engineCallOpt = inputMapper.map(tokens);

    if (engineCallOpt.isEmpty()) {
      return;
    }

    AbstractEngineCall engineCall = engineCallOpt.get();

    try {
      if (engineCall.shouldCallAsync()) {
        CompletableFuture.supplyAsync(engineCall)
            .thenAccept(
                engineResult -> {
                  if (!engineResult.isEmpty()) {
                    System.out.println(engineResult.getResult());
                  }
                })
            .get();
      } else {
        EngineResult engineResult = engineCall.get();
        if (!engineResult.isEmpty()) {
          System.out.println(engineResult.getResult());
        }
      }
    } catch (Exception e) {
      log.error("Error calling engine with input: {}, call:{}", input, engineCall, e);
    }
  }
}
