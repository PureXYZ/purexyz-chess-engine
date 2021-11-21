package com.purexyz.uci.input.token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** The enum Command input token. */
@Slf4j
@AllArgsConstructor
public enum CommandInputToken implements InputToken {
  /** Uci command input token. */
  UCI("uci"),
  /** Debug command input token. */
  DEBUG("debug"),
  /** Debug on command input token. */
  DEBUG_ON("on"),
  /** Debug off command input token. */
  DEBUG_OFF("off"),
  /** Is ready command input token. */
  IS_READY("isready"),
  /** Set option command input token. */
  SET_OPTION("setoption"),
  /** Set option name command input token. */
  SET_OPTION_NAME("name"),
  /** Set option value command input token. */
  SET_OPTION_VALUE("value"),
  /** Register command input token. */
  REGISTER("register"),
  /** Register later command input token. */
  REGISTER_LATER("later"),
  /** Register name command input token. */
  REGISTER_NAME("name"),
  /** Register code command input token. */
  REGISTER_CODE("code"),
  /** Uci new game command input token. */
  UCI_NEW_GAME("ucinewgame"),
  /** Position command input token. */
  POSITION("position"),
  /** Position fen command input token. */
  POSITION_FEN("fen"),
  /** Position start pos command input token. */
  POSITION_START_POS("startpos"),
  /** Position moves command input token. */
  POSITION_MOVES("moves"),
  /** Go command input token. */
  GO("go"),
  /** Go search moves command input token. */
  GO_SEARCH_MOVES("searchmoves"),
  /** Go ponder command input token. */
  GO_PONDER("ponder"),
  /** Go w time command input token. */
  GO_W_TIME("wtime"),
  /** Go b time command input token. */
  GO_B_TIME("btime"),
  /** Go w inc command input token. */
  GO_W_INC("winc"),
  /** Go b inc command input token. */
  GO_B_INC("binc"),
  /** Go moves to go command input token. */
  GO_MOVES_TO_GO("movestogo"),
  /** Go depth command input token. */
  GO_DEPTH("depth"),
  /** Go nodes command input token. */
  GO_NODES("nodes"),
  /** Go mate command input token. */
  GO_MATE("mate"),
  /** Go move time command input token. */
  GO_MOVE_TIME("movetime"),
  /** Go infinite command input token. */
  GO_INFINITE("infinite"),
  /** Stop command input token. */
  STOP("stop"),
  /** Ponder hit command input token. */
  PONDER_HIT("ponderhit"),
  /** Quit command input token. */
  QUIT("quit");

  /** The constant inputTokenMap. */
  private static final Map<String, CommandInputToken> inputTokenMap;

  static {
    log.info("Populating inputTokenMap");
    inputTokenMap = new HashMap<>();
    Arrays.stream(values()).forEach(t -> inputTokenMap.put(t.getValue(), t));
  }

  /** The Input. */
  private final String input;

  /**
   * Is command boolean.
   *
   * @param inputString the input string
   * @return the boolean
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
   * Of command input token.
   *
   * @param inputString the input string
   * @return the command input token
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

  /**
   * Gets value.
   *
   * @return the value
   */
  @Override
  public String getValue() {
    return input;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  @Override
  public Type getType() {
    return Type.COMMAND;
  }
}
