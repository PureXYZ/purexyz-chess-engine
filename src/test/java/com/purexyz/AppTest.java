package com.purexyz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void main() {
    assertDoesNotThrow(() -> App.main(null));
  }
}