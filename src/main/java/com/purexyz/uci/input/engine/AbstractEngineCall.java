package com.purexyz.uci.input.engine;

import java.util.function.Supplier;
import lombok.Getter;

@Getter
public abstract class AbstractEngineCall implements Supplier<EngineResult> {

  public abstract boolean shouldCallAsync();
}
