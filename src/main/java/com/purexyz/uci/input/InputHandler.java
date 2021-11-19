package com.purexyz.uci.input;

import com.purexyz.uci.input.token.InputToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

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
    Optional<Callable<String>> engineCall = inputMapper.map(tokens);

    if (engineCall.isEmpty()) {
      return;
    }

    try {
        String result = engineCall.get().call();
        System.out.println(result);
    } catch (Exception e) {
      log.error("Error calling engine with input: {}, call:{}", input, engineCall.get(), e);
    }
  }
}
