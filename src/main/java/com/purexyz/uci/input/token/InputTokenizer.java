package com.purexyz.uci.input.token;

import java.util.ArrayList;
import java.util.List;

public class InputTokenizer {

  private static final String WHITESPACE_REGEX = "\\s+";

  public List<InputToken> tokenize(String inputLine) {

    if (inputLine == null) {
      return List.of();
    }

    inputLine = normalize(inputLine);

    List<InputToken> tokens = new ArrayList<>();

    String[] split = inputLine.split(WHITESPACE_REGEX);
    for (String word : split) {
      if (CommandInputToken.isCommand(word)) {
        tokens.add(CommandInputToken.of(word));
      } else {
        tokens.add(new UserInputToken(word));
      }
    }

    return tokens;
  }

  private String normalize(String string) {
    string = string.strip();
    string = string.toLowerCase();
    return string;
  }
}
