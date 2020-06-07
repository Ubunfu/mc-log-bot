package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class AchievementLogParserTest {

    private static final String PLAYER = "PLAYER_001";
    private static final String ACHIEVMENT = "Who's the Pillager Now?";
    private static final String LOG_VALID = "[01:27:02] [Server thread/INFO]: " + PLAYER + " has made the advancement [" + ACHIEVMENT + "]";
    private Field fieldPlayer;
    private Field fieldAchievement;

    private AchievementLogParser logParser;

    @BeforeEach
    void setUp() {
        logParser = new AchievementLogParser();
        fieldPlayer = new Field("Player", PLAYER);
        fieldAchievement = new Field("Achievement", ACHIEVMENT);
    }

    @Test
    void whenValidLogExpectValidParserResponse() {
        ParserResponse parserResponse = logParser.parse(LOG_VALID);
        assertThat(parserResponse.getFields(), containsInAnyOrder(equalTo(fieldPlayer), equalTo(fieldAchievement)));
    }
}
