package com.github.ubunfu.service;

import com.github.ubunfu.handler.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class LogScraperService {

    private final Set<LogHandler> logHandlers;

    @Autowired
    public LogScraperService(Set<LogHandler> logHandlers) {
        this.logHandlers = logHandlers;
    }

    public void handleLog(String logMessage) {
        getMatchingLogHandlers(logMessage)
        .forEach(logHandler -> logHandler.handle(logMessage));
    }

    private Set<LogHandler> getMatchingLogHandlers(String logMessage) {
        return logHandlers.stream()
                .filter(logHandler -> logHandler.isMatch(logMessage))
                .collect(Collectors.toSet());
    }
}
