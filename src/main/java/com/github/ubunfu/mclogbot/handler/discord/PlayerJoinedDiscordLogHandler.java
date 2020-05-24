package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequestBuilder;
import com.github.ubunfu.mclogbot.config.properties.PlayerJoinedBotProperties;
import com.github.ubunfu.mclogbot.parser.LogParser;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PlayerJoinedDiscordLogHandler extends AbstractDiscordLogHandler {

    private static final String KEY_EXPRESSION = "joined the game";
    private final PlayerJoinedBotProperties playerJoinedBotProperties;

    @Autowired
    public PlayerJoinedDiscordLogHandler(
            DiscordClient discordClient,
            LogParser logParser,
            PlayerJoinedBotProperties playerJoinedBotProperties) {
        super(discordClient, logParser);
        this.playerJoinedBotProperties = playerJoinedBotProperties;
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
                .author(playerJoinedBotProperties.getAuthor())
                .thumbnailUrl(playerJoinedBotProperties.getThumbnailUrl())
                .title(playerJoinedBotProperties.getTitle())
                .color(playerJoinedBotProperties.getColor())
                .fields(parserResponse.getFields())
                .timestamp(ZonedDateTime.now())
                .build();
    }
}
