package com.github.ubunfu.parser;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class PlayerJoinedLogParser implements LogParser {

    public ParserResponse parse(String logMessage) {
        return new ParserResponse(new HashMap<>(), new HashSet<>());
    }
}
