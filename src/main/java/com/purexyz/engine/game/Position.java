package com.purexyz.engine.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Position {

  private Position() {}

  private final long[][] bitBoards = new long[Side.getTOTAL()][Piece.getTOTAL()];

  public Position defaultPosition() {
    Position position = new Position();
    // TODO setup defaults
    return position;
  }

  public long getBitBoard(Side side, Piece piece) {
    return bitBoards[side.getIntValue()][piece.getValue()];
  }

  public long[] getBitBoards(Side side) {
    return bitBoards[side.getIntValue()];
  }

  @Getter
  @AllArgsConstructor
  enum Side {
    WHITE(true),
    BLACK(false);

    private boolean value;

    private int getIntValue() {
      return value ? 0 : 1;
    }

    @Getter
    private static final int TOTAL = 2;
  }

  @Getter
  @AllArgsConstructor
  enum Piece {
    PAWN(0),
    KNIGHT(1),
    BISHOP(2),
    ROOK(3),
    QUEEN(4),
    KING(5);

    private int value;

    @Getter
    private static final int TOTAL = 6;
  }
}
