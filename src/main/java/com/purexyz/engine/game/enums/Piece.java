package com.purexyz.engine.game.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Piece {
  PAWN('p'),
  KNIGHT('n'),
  BISHOP('b'),
  ROOK('r'),
  QUEEN('q'),
  KING('k');

  private char value;

  private static final Map<Character, Piece> charMap = new HashMap<>();

  static {
    for (Piece piece : values()) {
      charMap.put(piece.getValue(), piece);
    }
  }

  public static final int TOTAL = values().length;

  public static Piece fromChar(char pieceChar) {
    return charMap.get(pieceChar);
  }
}
