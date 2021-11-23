package com.purexyz.engine.game;

import com.purexyz.engine.game.enums.Square;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class BitBoardUtils {

  private BitBoardUtils() {}

  public static final long EMPTY = 0L;
  public static final long UNIVERSAL = -1L;

  public static final long STARTING_WHITE_PAWNS = 65280L;
  public static final long STARTING_WHITE_KNIGHTS = 66L;
  public static final long STARTING_WHITE_BISHOPS = 36L;
  public static final long STARTING_WHITE_ROOKS = 129L;
  public static final long STARTING_WHITE_QUEEN = 8L;
  public static final long STARTING_WHITE_KING = 16L;

  public static final long STARTING_BLACK_PAWNS = 71776119061217280L;
  public static final long STARTING_BLACK_KNIGHTS = 4755801206503243776L;
  public static final long STARTING_BLACK_BISHOPS = 2594073385365405696L;
  public static final long STARTING_BLACK_ROOKS = -9151314442816847872L;
  public static final long STARTING_BLACK_QUEEN = 576460752303423488L;
  public static final long STARTING_BLACK_KING = 1152921504606846976L;

  public static String toBinaryString(long bitBoard) {
    return StringUtils.leftPad(Long.toBinaryString(bitBoard), Square.getTotal(), "0");
  }

  public static String toPrettyString(long bitBoard) {
    List<String> reversedLines =
        Arrays.stream(splitStringEvery(toBinaryString(bitBoard), Square.getRowTotal()))
            .map(StringUtils::reverse)
            .toList();
    return String.join("\n", reversedLines);
  }

  private static String[] splitStringEvery(String s, int interval) {
    int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
    String[] result = new String[arrayLength];

    int j = 0;
    int lastIndex = result.length - 1;
    for (int i = 0; i < lastIndex; i++) {
      result[i] = s.substring(j, j + interval);
      j += interval;
    } //Add the last bit
    result[lastIndex] = s.substring(j);

    return result;
  }
}
