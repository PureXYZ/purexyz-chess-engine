package com.purexyz;

import com.purexyz.uci.InputHandler;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final InputHandler inputHandler = InputHandler.getInstance();

  public static void main(String[] args) {
    printArt();

    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      String input = scanner.nextLine();
      log.info("Received input: {}", input);
      inputHandler.handleInput(input);
    }
  }

  private static void printArt() {
    log.info("Starting purexyz-chess-engine by PureXYZ");
    log.info("");
    log.info("   |\\_");
    log.info("  /  .\\_");
    log.info(" |   ___)");
    log.info(" |    \\");
    log.info(" |  =  |");
    log.info(" /_____\\");
    log.info("[_______]");
    log.info("");
  }
}
