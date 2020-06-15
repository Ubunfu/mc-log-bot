package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequestBuilder;
import com.github.ubunfu.mclogbot.config.properties.BotProperties;
import com.github.ubunfu.mclogbot.enums.DeathEnum;
import com.github.ubunfu.mclogbot.parser.LogParser;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

import static java.lang.String.format;

@Component
@ConditionalOnProperty(name = "bot.death.enabled", havingValue = "true")
public class DeathDiscordLogHandler extends AbstractDiscordLogHandler {

    private static final String BOT_NAME = "Death Bot";
    private static final Logger LOGGER = LoggerFactory.getLogger(DeathDiscordLogHandler.class);
    private final BotProperties botProperties;

    @Autowired
    public DeathDiscordLogHandler(
            DiscordClient discordClient,
            @Qualifier("deathBotLogParser") LogParser logParser,
            @Qualifier("deathBotProperties") BotProperties botProperties) {
        super(discordClient, logParser);
        this.botProperties = botProperties;
    }

    @Override
    public boolean isMatch(String logMessage) {
        for (DeathEnum deathEnum : DeathEnum.values()) {
            if (logMessage.contains(deathEnum.deathMessage())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handle(String logMessage) {
        LOGGER.debug(format("Handler %s handling log message: %s ...",
                DeathDiscordLogHandler.class.getCanonicalName(), logMessage));
        Response response = discordClient.invokeWebhook(buildDiscordRequest(logMessage));
        LOGGER.info(format("Discord response: %s", response.status()));
    }

    private DiscordWebhookRequest buildDiscordRequest(String logMessage) {
        DiscordWebhookRequest request = DiscordWebhookRequestBuilder.create()
                .author(botProperties.getAuthor())
                .thumbnailUrl(botProperties.getThumbnailUrl())
                .title(getAlertTitle(logMessage))
                .color(botProperties.getColor())
                .timestamp(ZonedDateTime.now())
                .build();
        LOGGER.debug(format("Built %s: %s",
                request.getClass().getCanonicalName(), request.toString()));
        return request;
    }

    private String getAlertTitle(String logMessage) {
        return logMessage.split("]: ")[1];
    }

    @Override
    public String getBotName() {
        return BOT_NAME;
    }
}
