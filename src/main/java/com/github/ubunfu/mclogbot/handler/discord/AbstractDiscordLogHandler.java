package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.handler.LogHandler;
import com.github.ubunfu.mclogbot.parser.LogParser;

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
