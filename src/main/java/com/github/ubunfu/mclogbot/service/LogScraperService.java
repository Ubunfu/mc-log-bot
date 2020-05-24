package com.github.ubunfu.mclogbot.service;

import com.github.ubunfu.mclogbot.handler.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
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
