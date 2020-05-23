package com.github.ubunfu.handler.discord;

import com.github.ubunfu.client.discord.DiscordClient;
import com.github.ubunfu.handler.LogHandler;
import com.github.ubunfu.parser.LogParser;

public abstract class AbstractDiscordLogHandler implements LogHandler {

    protected DiscordClient discordClient;
    protected LogParser logParser;

    public AbstractDiscordLogHandler(
            DiscordClient discordClient,
            LogParser logParser) {
        this.discordClient = discordClient;
        this.logParser = logParser;
    }
}
