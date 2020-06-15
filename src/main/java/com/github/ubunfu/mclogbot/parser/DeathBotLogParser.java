package com.github.ubunfu.mclogbot.parser;

import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@NoArgsConstructor
@Component
@ConditionalOnProperty(name = "bot.death.enabled", havingValue = "true")
public class DeathBotLogParser extends AbstractLogParser {

    @Override
    public ParserResponse parse(String logMessage) {
        return new ParserResponse(new HashSet<>());
    }
}
