package com.purexyz.engine.game;

import com.purexyz.engine.game.bitboard.BitBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Position {

  private Position() {}

  private BitBoard[][] boards = new BitBoard[Side.getTOTAL()][Piece.getTOTAL()];

  public Position defaultPosition() {
    Position position = new Position();
    return position;
  }

  public BitBoard getBitBoard(Side side, Piece piece) {
    return boards[side.getValue()][piece.getValue()];
  }

  @Getter
  @AllArgsConstructor
  enum Side {
    WHITE(0),
    BLACK(1);

    private int value;

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
