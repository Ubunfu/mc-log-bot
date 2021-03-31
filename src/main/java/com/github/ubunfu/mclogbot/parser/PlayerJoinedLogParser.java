package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@ConditionalOnProperty(name = "bot.player-joined.enabled", havingValue = "true")
public class PlayerJoinedLogParser extends AbstractLogParser {

    private static final String SUFFIX_JOINED_THE_GAME = "joined the game";
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";

    @Override
    public ParserResponse parse(String logMessage) {
        fields.clear();
        fields.add(new Field("Player", getPlayerName(logMessage)));
        return new ParserResponse(fields);
    }

    private String getPlayerName(String logMessage) {
        String logTruncated = logMessage.replace(SUFFIX_JOINED_THE_GAME, EMPTY_STRING).trim();
        String[] logTruncatedParts = logTruncated.split(SPACE);
        return logTruncatedParts[logTruncatedParts.length - 1];
    }
}
