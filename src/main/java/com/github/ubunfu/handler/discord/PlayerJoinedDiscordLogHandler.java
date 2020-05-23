package com.github.ubunfu.handler.discord;

import com.github.ubunfu.client.discord.DiscordClient;
import com.github.ubunfu.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.client.discord.request.DiscordWebhookRequestBuilder;
import com.github.ubunfu.client.discord.request.Field;
import com.github.ubunfu.parser.LogParser;
import com.github.ubunfu.parser.ParserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayerJoinedDiscordLogHandler extends AbstractDiscordLogHandler {

    private static final String KEY_EXPRESSION = "joined the game";

    @Autowired
    public PlayerJoinedDiscordLogHandler(
            DiscordClient discordClient,
            LogParser logParser) {
        super(discordClient, logParser);
    }

    @Override
    public boolean isMatch(String logMessage) {
        return logMessage.contains(KEY_EXPRESSION);
    }

    @Override
    public void handle(String logMessage) {
        discordClient.invokeWebhook(buildDiscordRequest(logMessage));
    }

    private DiscordWebhookRequest buildDiscordRequest(String logMessage) {
        ParserResponse parserResponse = logParser.parse(logMessage);
        return DiscordWebhookRequestBuilder.create()
                .author(parserResponse.getAttributes().get("author"))
                .thumbnailUrl(parserResponse.getAttributes().get("thumbnail"))
                .title(parserResponse.getAttributes().get("title"))
                .color(Long.parseLong(parserResponse.getAttributes().get("color")))
                .fields(parserResponse.getFields())
                .timestamp(parserResponse.getAttributes().get("timestamp"))
                .build();
    }
}
