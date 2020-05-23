package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.discord.request.Field;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Component
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
