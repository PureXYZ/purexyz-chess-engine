package com.purexyz.uci.input.token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Input token for uci command. */
@Slf4j
@AllArgsConstructor
public enum CommandInputToken implements InputToken {
  UCI("uci"),
  DEBUG("debug"),
  DEBUG_ON("on"),
  DEBUG_OFF("off"),
  IS_READY("isready"),
  SET_OPTION("setoption"),
  SET_OPTION_NAME("name"),
  SET_OPTION_VALUE("value"),
  REGISTER("register"),
  REGISTER_LATER("later"),
  REGISTER_NAME("name"),
  REGISTER_CODE("code"),
  UCI_NEW_GAME("ucinewgame"),
  POSITION("position"),
  POSITION_FEN("fen"),
  POSITION_START_POS("startpos"),
  POSITION_MOVES("moves"),
  GO("go"),
  GO_SEARCH_MOVES("searchmoves"),
  GO_PONDER("ponder"),
  GO_W_TIME("wtime"),
  GO_B_TIME("btime"),
  GO_W_INC("winc"),
  GO_B_INC("binc"),
  GO_MOVES_TO_GO("movestogo"),
  GO_DEPTH("depth"),
  GO_NODES("nodes"),
  GO_MATE("mate"),
  GO_MOVE_TIME("movetime"),
  GO_INFINITE("infinite"),
  STOP("stop"),
  PONDER_HIT("ponderhit"),
  QUIT("quit");

  private final String input;

  private static final Map<String, CommandInputToken> inputTokenMap;

  static {
    log.info("Populating inputTokenMap");
    inputTokenMap = new HashMap<>();
    Arrays.stream(values()).forEach(t -> inputTokenMap.put(t.getValue(), t));
  }

  /**
   * Checks if input string is uci command.
   *
   * @param inputString Token string.
   * @return True input if is command.
   */
  public static boolean isCommand(String inputString) {
    if (inputString == null) {
      log.error("Input string is null");
      throw new NullPointerException();
    }

    CommandInputToken token = inputTokenMap.get(inputString);

    if (token == null) {
      log.warn("Command token not found with input: {}", inputString);
      return false;
    }

    return Type.COMMAND == token.getType();
  }

  /**
   * Get command token from input string.
   *
   * @param inputString String from user input.
   * @return CommandInputToken.
   */
  public static CommandInputToken of(String inputString) {
    if (inputString == null) {
      log.error("Input string is null");
      throw new NullPointerException();
    }

    CommandInputToken token = inputTokenMap.get(inputString);

    if (token == null) {
      log.error("Command token not found with input: {}", inputString);
      throw new IllegalArgumentException();
    }

    return token;
  }

  @Override
  public String getValue() {
    return input;
  }

  @Override
  public Type getType() {
    return Type.COMMAND;
  }
}
