package com.purexyz.uci.input.engine.calls;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/** The type Debug call. */
@Slf4j
@AllArgsConstructor
public class DebugCall extends AbstractEngineCall {

  /** The Value. */
  private String value;

  /**
   * Should call async boolean.
   *
   * @return the boolean
   */
  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  /**
   * Compute engine result.
   *
   * @return the engine result
   */
  @Override
  public EngineResult compute() {
    log.info("Computing debug call");

    if (value == null) {
      return EngineResult.emptyResult();
    }

    if (value.equals("on")) {
      changeConsoleLogging("System.out");
    } else if (value.equals("off")) {
      changeConsoleLogging("System.err");
    }

    return EngineResult.emptyResult();
  }

  /**
   * Change console logging.
   *
   * @param target the target
   */
  private void changeConsoleLogging(String target) {
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    ConsoleAppender<ILoggingEvent> appender =
        (ConsoleAppender<ILoggingEvent>) lc.getLogger("com.purexyz").getAppender("CONSOLE");
    appender.setTarget(target);
    appender.start();
  }
}
