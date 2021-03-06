package com.purexyz.uci.input;

import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.UserInputToken;
import java.util.ArrayDeque;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputTokenizer {

  private static final String WHITESPACE_REGEX = "\\s+";

  private static InputTokenizer instance;

  private InputTokenizer() {}

  public static InputTokenizer getInstance() {
    if (instance == null) {
      instance = new InputTokenizer();
    }

    return instance;
  }

  public Queue<InputToken> tokenize(String inputLine) {

    log.info("Tokenizing input: {}", inputLine);

    if (inputLine == null || inputLine.isBlank()) {
      log.info("Input is empty input: {}", inputLine);
      return new ArrayDeque<>();
    }

    inputLine = normalize(inputLine);

    Queue<InputToken> tokens = new ArrayDeque<>();

    String[] split = inputLine.split(WHITESPACE_REGEX);
    for (String word : split) {
      if (CommandInputToken.isCommand(word)) {
        log.info("Adding command token from word: {}", word);
        tokens.add(CommandInputToken.of(word));
      } else {
        log.info("Adding user input token from word: {}", word);
        tokens.add(new UserInputToken(word));
      }
    }

    return tokens;
  }

  private String normalize(String string) {
    return string.strip();
  }
}
