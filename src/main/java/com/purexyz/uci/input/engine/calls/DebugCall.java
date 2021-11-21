package com.purexyz.uci.input.engine.calls;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import com.purexyz.uci.input.engine.AbstractEngineCall;
import com.purexyz.uci.input.engine.EngineResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
@AllArgsConstructor
public class DebugCall extends AbstractEngineCall {

  private boolean debugOn;

  @Override
  public boolean shouldCallAsync() {
    return false;
  }

  @Override
  public EngineResult compute() {
    log.info("Computing debug call");

    if (debugOn) {
      changeConsoleLogging("System.out", Level.ALL);
    } else {
      changeConsoleLogging("System.err", Level.ERROR);
    }

    return EngineResult.emptyResult();
  }

  private void changeConsoleLogging(String target, Level level) {

    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    Logger logger = context.getLogger("com.purexyz");
    logger.setLevel(level);

    ConsoleAppender<ILoggingEvent> appender =
        (ConsoleAppender<ILoggingEvent>) logger.getAppender("CONSOLE");
    appender.setTarget(target);
    appender.start();
  }
}
