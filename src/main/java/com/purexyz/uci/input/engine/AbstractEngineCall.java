package com.purexyz.uci.input.engine;

import lombok.Getter;

import java.util.concurrent.Callable;

@Getter
public abstract class AbstractEngineCall implements Callable<EngineResult> {

  public abstract boolean shouldCallAsync();
}
