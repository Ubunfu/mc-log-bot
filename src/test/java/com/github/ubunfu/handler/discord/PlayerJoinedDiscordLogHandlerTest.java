package com.github.ubunfu.handler.discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ubunfu.client.discord.DiscordClient;
import com.github.ubunfu.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.client.discord.request.Field;
import com.github.ubunfu.parser.ParserResponse;
import com.github.ubunfu.parser.PlayerJoinedLogParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerJoinedDiscordLogHandlerTest {

    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final String LOG_PLAYER_LEFT = "[23:35:23] [Server thread/INFO]: PLAYER001 left the game";
    private static final String REQ_JSON_PLAYER_JOINED = "{\"embeds\":[{\"author\":{\"name\":\"AUTHOR_001\"},\"thumbnail\":{\"url\":\"THUMBNAIL_001\"},\"title\":\"TITLE_001\",\"color\":1,\"fields\":[{\"name\":\"FIELD_NAME\",\"value\":\"FIELD_VALUE\"}],\"timestamp\":\"TIMESTAMP_001\"}]}";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String AUTHOR = "AUTHOR_001";
    private static final String THUMBNAIL = "THUMBNAIL_001";
    private static final String TITLE = "TITLE_001";
    private static final long COLOR = 1;
    private static final String FIELD_1_NAME = "FIELD_NAME";
    private static final String FIELD_1_VALUE = "FIELD_VALUE";
    private static final Field FIELD_1 = new Field(FIELD_1_NAME, FIELD_1_VALUE);
    private static final String TIMESTAMP = "TIMESTAMP_001";
    private static final ParserResponse PARSER_RESPONSE = buildParserResponse();

    private PlayerJoinedDiscordLogHandler logHandler;

    private DiscordWebhookRequest discordWebhookRequest;

    private ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor = ArgumentCaptor.forClass(DiscordWebhookRequest.class);

    @Mock
    private PlayerJoinedLogParser logParser;

    @Mock
    private DiscordClient discordClient;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        logHandler = new PlayerJoinedDiscordLogHandler(discordClient, logParser);
        discordWebhookRequest = buildDiscordWebhookRequest();
    }

    private static Map<String, String> buildParsedAtrributes() {
        Map<String, String> map = new HashMap<>();
        map.put("author", AUTHOR);
        map.put("thumbnail", THUMBNAIL);
        map.put("title", TITLE);
        map.put("color", String.valueOf(COLOR));
        map.put("timestamp", TIMESTAMP);
        return map;
    }

    private static Set<Field> buildParsedLogFields() {
        Set<Field> fields = new HashSet<>();
        fields.add(FIELD_1);
        return fields;
    }

    private static ParserResponse buildParserResponse() {
        return new ParserResponse(buildParsedAtrributes(), buildParsedLogFields());
    }

    private DiscordWebhookRequest buildDiscordWebhookRequest() throws JsonProcessingException {
        return MAPPER.readValue(REQ_JSON_PLAYER_JOINED, DiscordWebhookRequest.class);
    }

    @Test
    void whenPlayerJoinedExpectHandlerMatches() {
        assertThat(logHandler.isMatch(LOG_PLAYER_JOINED), is(true));
    }

    @Test
    void whenPlayerDidNotJoinExpectHandlerDoesNotMatch() {
        assertThat(logHandler.isMatch(LOG_PLAYER_LEFT), is(false));
    }

    @Test
    void whenHandleLogExpectCallDiscord() {
        when(logParser.parse(anyString())).thenReturn(PARSER_RESPONSE);
        logHandler.handle(LOG_PLAYER_JOINED);
        verify(discordClient).invokeWebhook(discordReqCaptor.capture());
        assertThat(discordReqCaptor.getValue(), equalTo(discordWebhookRequest));
    }
}