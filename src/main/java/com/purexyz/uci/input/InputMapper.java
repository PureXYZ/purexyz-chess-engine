package com.purexyz.uci.input;

import com.purexyz.uci.input.token.InputToken;
import com.purexyz.uci.input.token.InputToken.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class InputMapper {

  public Optional<Callable<String>> map(List<InputToken> tokens) {

    if (tokens == null || tokens.isEmpty()) {
      return Optional.empty();
    }

    InputToken firstToken = tokens.get(0);
    if (Type.COMMAND != firstToken.getType()) {
      return Optional.empty();
    }



    return Optional.empty();
  }
}
