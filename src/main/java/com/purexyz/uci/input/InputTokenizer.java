package com.purexyz.uci.input;

import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.UserInputToken;

import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputTokenizer {

  private static InputTokenizer INSTANCE;

  private static final String WHITESPACE_REGEX = "\\s+";

  private InputTokenizer() {}

  public static InputTokenizer getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputTokenizer();
    }

    return INSTANCE;
  }

  public List<InputToken> tokenize(String inputLine) {

    log.info("Tokenizing input: {}", inputLine);

    if (inputLine == null || inputLine.isBlank()) {
      log.info("Input is empty input: {}", inputLine);
      return List.of();
    }

    inputLine = normalize(inputLine);

    List<InputToken> tokens = new LinkedList<>();

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
