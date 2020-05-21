package com.github.ubunfu.service;

import com.github.ubunfu.client.discord.DiscordWebhookClient;
import org.springframework.beans.factory.annotation.Autowired;

public class LogScraperService {

    private final DiscordWebhookClient discordClient;

    @Autowired
    public LogScraperService(DiscordWebhookClient discordClient) {
        this.discordClient = discordClient;
    }
}
