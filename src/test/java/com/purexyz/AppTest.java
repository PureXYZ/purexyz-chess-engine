package com.purexyz;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class AppTest {

  @Test
  void main() throws Exception {
    AtomicInteger statusCode = new AtomicInteger(1);
    withTextFromSystemIn("quit")
        .execute(
            () -> {
              statusCode.set(catchSystemExit(() -> App.main(null)));
            });
    assertEquals(0, statusCode.get());
  }
}
