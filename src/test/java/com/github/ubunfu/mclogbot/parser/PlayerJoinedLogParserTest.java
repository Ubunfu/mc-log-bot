package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class PlayerJoinedLogParserTest {

    private static final String PLAYER = "PLAYER_001";
    private static final String LOG_VANILLA = "[23:35:23] [Server thread/INFO]: " + PLAYER + " joined the game";
    private static final String LOG_MODDED_FORGE = "[29Mar2021 11:20:12.946] [Server thread/INFO] [net.minecraft.server.dedicated.DedicatedServer/]: " + PLAYER + " joined the game";
    private Field field;

    private PlayerJoinedLogParser logParser;

    @BeforeEach
    void setUp() {
        logParser = new PlayerJoinedLogParser();
        field = new Field("Player", PLAYER);
    }

    @Test
    void whenValidLogExpectValidParserResponse() {
        ParserResponse parserResponse = logParser.parse(LOG_VANILLA);
        assertThat(parserResponse.getFields(), containsInAnyOrder(equalTo(field)));
    }

    @Test
    void whenModdedForgeLogExpectValidParserResponse() {
        ParserResponse parserResponse = logParser.parse(LOG_MODDED_FORGE);
        assertThat(parserResponse.getFields(), containsInAnyOrder(equalTo(field)));
    }
}
