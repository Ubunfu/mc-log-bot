package com.github.ubunfu.handler;

public interface LogHandler {
    boolean isMatch(String logMessage);

    void handle(String anyString);
}
