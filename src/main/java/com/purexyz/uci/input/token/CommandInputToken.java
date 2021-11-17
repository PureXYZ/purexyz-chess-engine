package com.purexyz.uci.input.token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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

  String input;

  private static final Map<String, CommandInputToken> inputTokenMap;

  static {
    inputTokenMap = new HashMap<>();
    Arrays.stream(values()).forEach(t -> inputTokenMap.put(t.getValue(), t));
  }

  public static boolean isCommand(String inputString) {

    if (inputString == null) {
      return false;
    }

    CommandInputToken token = inputTokenMap.get(inputString);

    if (token == null) {
      return false;
    }

    return Type.COMMAND == token.getType();
  }

  public static CommandInputToken of(String inputString) {

    if (inputString == null) {
      throw new NullPointerException();
    }

    CommandInputToken token = inputTokenMap.get(inputString);

    if (token == null) {
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
