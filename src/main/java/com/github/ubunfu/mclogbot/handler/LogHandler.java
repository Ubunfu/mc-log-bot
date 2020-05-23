package com.github.ubunfu.mclogbot.handler;

public interface LogHandler {
    boolean isMatch(String logMessage);

    void handle(String anyString);
}
