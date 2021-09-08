package io.opentelemetry.javaagent.bootstrap;

import org.slf4j.Logger;

public interface PatchLogger extends Logger {

  public static boolean DETERMINE_NATIVE_STARTUP = true;

  public static PatchLogger of(String name) {
    if (DETERMINE_NATIVE_STARTUP) {
      return new PatchLoggerJul();
    }
    return PatchLoggerSlf4j.getLogger(name);
  }
}
