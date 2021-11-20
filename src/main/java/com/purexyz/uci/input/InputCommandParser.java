package com.purexyz.uci.input;

import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.calls.QuitCall;
import com.purexyz.uci.input.token.CommandInputToken;
import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputCommandParser {

  private static InputCommandParser INSTANCE;

  private final static Map<CommandInputToken, List<InputToken>> commandStructureMapping = new HashMap<>();

  static {
    fillCommandStructureMapping();
  }

  private InputCommandParser() {}

  public static InputCommandParser getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InputCommandParser();
    }

    return INSTANCE;
  }

  public static Optional<AbstractEngineCall> getEngineCall(List<InputToken> inputTokens) {

    log.info("Parsing tokens: {}", inputTokens);

    if (!validateInputTokens(inputTokens)) {
      return Optional.empty();
    }

    CommandInputToken command = (CommandInputToken) inputTokens.get(0);
    List<InputToken> commandStructure = getCommandStructure(command);

    log.info("Found command: {}", command);

    if (!validateStructure(inputTokens, commandStructure)) {
      return Optional.empty();
    }

    if (command == CommandInputToken.QUIT) {
      log.info("Creating quit engine call");
      return Optional.of(new QuitCall());
    }

    log.warn("No engine call found");
    return Optional.empty();
  }

  private static boolean validateInputTokens(List<InputToken> inputTokens) {
    if (inputTokens == null || inputTokens.isEmpty()) {
      log.warn("Tokens are empty");
      return false;
    }

    if (inputTokens.get(0).getType() != Type.COMMAND) {
      log.warn("First token must be command");
      return false;
    }

    return true;
  }

  private static boolean validateStructure(List<InputToken> inputTokens, List<InputToken> commandStructure) {
    int inputTokensPointer = 0;
    int commandStructurePointer = 0;

    for (int i = 0; i < commandStructure.size(); i++) {
     // TODO
    }

    return true;
  }

  private static List<InputToken> getCommandStructure(CommandInputToken command) {
    if (!commandStructureMapping.containsKey(command)) {
      log.warn("Command not found with token: {}", command);
      return List.of();
    }

    return commandStructureMapping.get(command);
  }

  private static void fillCommandStructureMapping() {
    commandStructureMapping.put(CommandInputToken.QUIT, List.of(CommandInputToken.QUIT));
  }

}
