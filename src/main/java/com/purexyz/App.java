package com.purexyz;

import com.purexyz.uci.InputHandler;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

/** Uci chess engine. */
@Slf4j
public class App {

  private static final InputHandler inputHandler = InputHandler.getInstance();

  /**
   * Listens for input from stdin.
   * Expects uci commands.
   *
   * @param args Program arguments. */
  public static void main(String[] args) {
    log.info("Starting application");

    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      String input = scanner.nextLine();
      log.info("Received input: {}", input);
      inputHandler.handleInput(input);
    }
  }
}
