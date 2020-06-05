package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.handler.LogHandler;
import com.github.ubunfu.mclogbot.parser.LogParser;

import static java.lang.String.format;

public abstract class AbstractDiscordLogHandler implements LogHandler {

    private static final String HELLO_FORMAT = "Bot <%s> is enabled!";

    protected DiscordClient discordClient;
    protected LogParser logParser;

    public AbstractDiscordLogHandler(
            DiscordClient discordClient,
            LogParser logParser) {
        this.discordClient = discordClient;
        this.logParser = logParser;
    }

    @Override
    public String getWelcomeMessage() {
        return format(HELLO_FORMAT, getBotName());
    }
}
