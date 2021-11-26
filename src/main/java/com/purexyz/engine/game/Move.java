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
  private Piece promotion;

  private static final Move NULL_MOVE = new Move(null, null);

  public Move(Square from, Square to, Piece promotion) {
    setFrom(from);
    setTo(to);
    setPromotion(promotion);
  }

  public Move(Square from, Square to) {
    setFrom(from);
    setTo(to);
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

    Square from = Square.valueOf(moveString.substring(0, 2));
    Square to = Square.valueOf(moveString.substring(2, 4));
    Piece promotion = null;

    if (moveString.length() > 4) {
      promotion = Piece.fromChar(moveString.charAt(4));
    }

    return new Move(from, to, promotion);
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
