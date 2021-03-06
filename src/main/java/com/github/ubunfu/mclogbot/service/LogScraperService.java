package com.github.ubunfu.mclogbot.service;

import com.github.ubunfu.mclogbot.handler.LogHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LogScraperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogScraperService.class);

    private final Set<LogHandler> logHandlers;

    @Autowired
    public LogScraperService(Set<LogHandler> logHandlers) {
        this.logHandlers = logHandlers;
    }

    @PostConstruct
    private void welcomeBots() {
        logHandlers.forEach(logHandler -> LOGGER.info(logHandler.getWelcomeMessage()));
    }

    public void handleLog(String logMessage) {
        getMatchingLogHandlers(logMessage)
        .forEach(logHandler -> logHandler.handle(logMessage));
    }

    private Set<LogHandler> getMatchingLogHandlers(String logMessage) {
        LOGGER.debug("Searching for capable LogHandlers ...");
        return logHandlers.stream()
                .filter(logHandler -> logHandler.isMatch(logMessage))
                .collect(Collectors.toSet());
    }
}
