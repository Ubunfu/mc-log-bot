package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.discord.DiscordClient;
import com.github.ubunfu.mclogbot.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.discord.request.DiscordWebhookRequestBuilder;
import com.github.ubunfu.mclogbot.properties.PlayerJoinedTileProperties;
import com.github.ubunfu.mclogbot.parser.LogParser;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayerJoinedDiscordLogHandler extends AbstractDiscordLogHandler {

    private static final String KEY_EXPRESSION = "joined the game";
    private final PlayerJoinedTileProperties playerJoinedTileProperties;

    @Autowired
    public PlayerJoinedDiscordLogHandler(
            DiscordClient discordClient,
            LogParser logParser,
            PlayerJoinedTileProperties playerJoinedTileProperties) {
        super(discordClient, logParser);
        this.playerJoinedTileProperties = playerJoinedTileProperties;
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
                .author(playerJoinedTileProperties.getAuthor())
                .thumbnailUrl(playerJoinedTileProperties.getThumbnailUrl())
                .title(playerJoinedTileProperties.getTitle())
                .color(playerJoinedTileProperties.getColor())
                .fields(parserResponse.getFields())
                .timestamp(new Date())
                .build();
    }
}
