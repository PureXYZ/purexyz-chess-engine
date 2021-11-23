package com.purexyz.engine.game;

import com.purexyz.engine.game.enums.Piece;
import com.purexyz.engine.game.enums.Side;

public class Position {

  private Position() {}

  private final long[][] bitBoards = new long[Side.getTOTAL()][Piece.getTOTAL()];

  public Position defaultPosition() {
    Position position = new Position();
    // TODO setup defaults
    return position;
  }

  public long getBitBoard(Side side, Piece piece) {
    return bitBoards[side.ordinal()][piece.ordinal()];
  }

  public long[] getBitBoards(Side side) {
    return bitBoards[side.ordinal()];
  }
}
