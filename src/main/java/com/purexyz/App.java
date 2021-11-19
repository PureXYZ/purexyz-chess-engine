package com.purexyz;

import com.purexyz.uci.InputHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/** Hello world program. */
@Slf4j
public class App {

  private static final InputHandler inputHandler = InputHandler.getInstance();

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      String input = scanner.nextLine();
      inputHandler.handleInput(input);
    }
  }
}
