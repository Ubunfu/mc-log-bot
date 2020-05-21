package com.github.ubunfu.service;

import com.github.ubunfu.client.discord.DiscordWebhookClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogScraperServiceTest {

    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final String LOG_PLAYER_LEFT = "[23:35:23] [Server thread/INFO]: PLAYER001 left the game";
    private static final String LOG_PLAYER_EARNED_ACHIEVEMENT =
            "[01:27:02] [Server thread/INFO]: PLAYER001 has made the advancement [Who's the Pillager Now?]";
    private static final String LOG_PLAYER_DIED = "";


    @Mock
    private DiscordWebhookClient discordClient;

    private LogScraperService logScraperService;

    @BeforeEach
    void setUp() {
        this.logScraperService = new LogScraperService(discordClient);
    }

    @Test
    void whenPlayerJoinsExpectDiscordPost() {
        logScraperService.handleLog();
    }

    @Test
    void whenPlayerLeavesExpectDiscordPost() {
    }

    @Test
    void whenPlayerDiesExpectDiscordPost() {
    }

    @Test
    void whenPlayerEarnsAchievementExpectDiscordPost() {
    }
}
