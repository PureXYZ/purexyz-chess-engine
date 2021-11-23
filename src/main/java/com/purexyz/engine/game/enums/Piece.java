package com.purexyz.engine.game.enums;

import lombok.Getter;

public enum Piece {
  PAWN,
  KNIGHT,
  BISHOP,
  ROOK,
  QUEEN,
  KING;

  @Getter
  private static final int TOTAL = 6;
}
