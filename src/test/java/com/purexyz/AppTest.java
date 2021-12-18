package com.purexyz;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AppTest {

  static InputStream inputStream;

  @BeforeAll
  static void setUp() {
    inputStream = System.in;
  }

  @AfterAll
  static void tearDown() {
    System.setIn(inputStream);
  }

  void setSystemInput(String... inputs) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String input : inputs) {
      stringBuilder.append(input);
      stringBuilder.append(System.lineSeparator());
    }

    byte[] input = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
    System.setIn(byteArrayInputStream);
  }

  @Test
  void main() {
    setSystemInput("quit");
    assertDoesNotThrow(() -> App.main(new String[]{}));
  }
}
