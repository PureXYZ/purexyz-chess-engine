package com.purexyz.engine.game;

import com.purexyz.engine.game.enums.Piece;
import com.purexyz.engine.game.enums.Side;

public class Position {

  private Position() {}

  private final long[][] bitBoards = new long[Side.getTotal()][Piece.getTotal()];

  public static Position defaultPosition() {
    Position position = new Position();

    position.setBitBoard(Side.WHITE, Piece.PAWN, BitBoardUtils.STARTING_WHITE_PAWNS);
    position.setBitBoard(Side.WHITE, Piece.KNIGHT, BitBoardUtils.STARTING_WHITE_KNIGHTS);
    position.setBitBoard(Side.WHITE, Piece.BISHOP, BitBoardUtils.STARTING_WHITE_BISHOPS);
    position.setBitBoard(Side.WHITE, Piece.ROOK, BitBoardUtils.STARTING_WHITE_ROOKS);
    position.setBitBoard(Side.WHITE, Piece.QUEEN, BitBoardUtils.STARTING_WHITE_QUEEN);
    position.setBitBoard(Side.WHITE, Piece.KING, BitBoardUtils.STARTING_WHITE_KING);

    position.setBitBoard(Side.BLACK, Piece.PAWN, BitBoardUtils.STARTING_BLACK_PAWNS);
    position.setBitBoard(Side.BLACK, Piece.KNIGHT, BitBoardUtils.STARTING_BLACK_KNIGHTS);
    position.setBitBoard(Side.BLACK, Piece.BISHOP, BitBoardUtils.STARTING_BLACK_BISHOPS);
    position.setBitBoard(Side.BLACK, Piece.ROOK, BitBoardUtils.STARTING_BLACK_ROOKS);
    position.setBitBoard(Side.BLACK, Piece.QUEEN, BitBoardUtils.STARTING_BLACK_QUEEN);
    position.setBitBoard(Side.BLACK, Piece.KING, BitBoardUtils.STARTING_BLACK_KING);

    return position;
  }

  public long getBitBoard(Side side, Piece piece) {
    return bitBoards[side.ordinal()][piece.ordinal()];
  }

  public void setBitBoard(Side side, Piece piece, long bitBoard) {
    bitBoards[side.ordinal()][piece.ordinal()] = bitBoard;
  }

  public long[] getBitBoards(Side side) {
    return bitBoards[side.ordinal()];
  }
}
