package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequestBuilder;
import com.github.ubunfu.mclogbot.config.properties.PlayerJoinedBotProperties;
import com.github.ubunfu.mclogbot.parser.LogParser;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

import static java.lang.String.format;

@Component
@ConditionalOnProperty(name = "bot.player-joined.enabled", havingValue = "true")
public class PlayerJoinedDiscordLogHandler extends AbstractDiscordLogHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerJoinedDiscordLogHandler.class);

    private static final String BOT_NAME = "Player Joined Bot";
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
        LOGGER.debug(format("Testing log handling capability of LogHandler: %s ... %b",
                PlayerJoinedDiscordLogHandler.class.getCanonicalName(), logMessage.contains(KEY_EXPRESSION)));
        return logMessage.contains(KEY_EXPRESSION);
    }

    @Override
    public void handle(String logMessage) {
        LOGGER.debug(format("Handler %s handling log message: %s ...",
                PlayerJoinedDiscordLogHandler.class.getCanonicalName(), logMessage));
        Response response = discordClient.invokeWebhook(buildDiscordRequest(logMessage));
        LOGGER.info(format("Discord response: %s", response.status()));
    }

    @Override
    public String getBotName() {
        return BOT_NAME;
    }

    private DiscordWebhookRequest buildDiscordRequest(String logMessage) {
        ParserResponse parserResponse = logParser.parse(logMessage);
        LOGGER.debug(format("Parsed log message into: %s", parserResponse.toString()));
        DiscordWebhookRequest request = DiscordWebhookRequestBuilder.create()
                .author(playerJoinedBotProperties.getAuthor())
                .thumbnailUrl(playerJoinedBotProperties.getThumbnailUrl())
                .title(playerJoinedBotProperties.getTitle())
                .color(playerJoinedBotProperties.getColor())
                .fields(parserResponse.getFields())
                .timestamp(ZonedDateTime.now())
                .build();
        LOGGER.debug(format("Built %s: %s",
                request.getClass().getCanonicalName(), request.toString()));
        return request;
    }
}
