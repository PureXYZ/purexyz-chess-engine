package com.purexyz.engine.game;

import com.purexyz.engine.game.enums.Piece;
import com.purexyz.engine.game.enums.Square;
import com.purexyz.exceptions.InternalEngineException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class Move {

  private Square from;
  private Square to;
  private Piece piece;
  private Piece promotion;

  public static final Move NULL_MOVE = new Move();

  private Move() {}

  public Move(Square from, Square to, Piece piece, Piece promotion) {
    setFrom(from);
    setTo(to);
    setPiece(piece);
    setPromotion(promotion);
  }

  public void setPromotion(Piece piece) {
    if (piece == Piece.KING || piece == Piece.PAWN) {
      throw new InternalEngineException("Invalid promotion piece");
    }

    promotion = piece;
  }

  public static Move fromString(String moveString) {
    moveString = moveString.strip().toLowerCase();

    if (moveString.equals("0000")) {
      return NULL_MOVE;
    }

    // TODO Generate moves, then get the one that matches
    return NULL_MOVE;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append(from.name());
    builder.append(to.name());

    if (promotion != null) {
      builder.append(promotion.getValue());
    }

    return builder.toString();
  }
}
