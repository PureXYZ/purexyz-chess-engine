package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import com.purexyz.uci.input.token.InputToken;
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
        // TODO handle async
      } else {
        EngineResult result = engineCall.call();
        if (!result.isEmpty()) {
          System.out.println(result.getResult());
        }
      }
    } catch (Exception e) {
      log.error("Error calling engine with input: {}, call:{}", input, engineCall, e);
    }
  }
}
