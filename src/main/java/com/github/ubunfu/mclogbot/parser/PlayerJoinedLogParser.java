package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Component
@ConditionalOnProperty(name = "bot.player-joined.enabled", havingValue = "true")
public class PlayerJoinedLogParser implements LogParser {

    private Set<Field> fields = new HashSet<>();

    public ParserResponse parse(String logMessage) {
        fields.add(new Field("Player", getPlayerName(logMessage)));
        return new ParserResponse(fields);
    }

    private String getPlayerName(String logMessage) {
        return logMessage.split(" ")[3];
    }
}
