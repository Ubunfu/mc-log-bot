package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.Field;
import com.github.ubunfu.mclogbot.config.properties.PlayerJoinedBotProperties;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import com.github.ubunfu.mclogbot.parser.PlayerJoinedLogParser;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerJoinedDiscordLogHandlerTest {

    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final String LOG_PLAYER_LEFT = "[23:35:23] [Server thread/INFO]: PLAYER001 left the game";
    private static final String AUTHOR = "AUTHOR_001";
    private static final String THUMBNAIL_URL = "THUMBNAIL_001";
    private static final String TITLE = "TITLE_001";
    private static final long COLOR = 1;
    private static final String FIELD_1_NAME = "FIELD_NAME_001";
    private static final String FIELD_1_VALUE = "FIELD_VALUE_001";
    private static final Field FIELD_1 = new Field(FIELD_1_NAME, FIELD_1_VALUE);
    private static final ParserResponse PARSER_RESPONSE = buildParserResponse();
    private static final Response FEIGN_RESPONSE = buildFeignResponse();

    private PlayerJoinedDiscordLogHandler logHandler;
    private ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor = ArgumentCaptor.forClass(DiscordWebhookRequest.class);

    @Mock
    private PlayerJoinedBotProperties properties;

    @Mock
    private PlayerJoinedLogParser logParser;

    @Mock
    private DiscordClient discordClient;

    @BeforeEach
    void setUp() {
        logHandler = new PlayerJoinedDiscordLogHandler(discordClient, logParser, properties);
    }

    private static Response buildFeignResponse() {
        return Response.builder()
                .request(buildFeignRequest())
                .build();
    }

    private static Request buildFeignRequest() {
        return Request.create(
                Request.HttpMethod.POST,
                "http://localhost",
                new HashMap<>(),
                Request.Body.empty(),
                null);
    }

    private static Set<Field> buildParsedLogFields() {
        Set<Field> fields = new HashSet<>();
        fields.add(FIELD_1);
        return fields;
    }

    private static ParserResponse buildParserResponse() {
        return new ParserResponse(buildParsedLogFields());
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
    void whenHandleLogExpectParseLogAndCallDiscord() {
        when(discordClient.invokeWebhook(any()))
                .thenReturn(FEIGN_RESPONSE);
        configureMockProperties();
        when(logParser.parse(anyString())).thenReturn(PARSER_RESPONSE);
        logHandler.handle(LOG_PLAYER_JOINED);
        verify(logParser).parse(LOG_PLAYER_JOINED);
        verify(discordClient).invokeWebhook(discordReqCaptor.capture());
        verifyDiscordRequest(discordReqCaptor);
    }

    private void configureMockProperties() {
        when(properties.getAuthor()).thenReturn(AUTHOR);
        when(properties.getThumbnailUrl()).thenReturn(THUMBNAIL_URL);
        when(properties.getTitle()).thenReturn(TITLE);
        when(properties.getColor()).thenReturn(COLOR);
    }

    private void verifyDiscordRequest(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor) {
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getAuthor().getName(), equalTo(AUTHOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getThumbnail().getUrl(), equalTo(THUMBNAIL_URL));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getTitle(), equalTo(TITLE));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getColor(), equalTo(COLOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getFields(), hasSize(1));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getFields(), contains(FIELD_1));
        verifyTimestampFormat(discordReqCaptor);
    }

    private void verifyTimestampFormat(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor) {
        String timestampString = discordReqCaptor.getValue().getEmbeds()[0].getTimestamp();
        ZonedDateTime.parse(timestampString);
    }
}