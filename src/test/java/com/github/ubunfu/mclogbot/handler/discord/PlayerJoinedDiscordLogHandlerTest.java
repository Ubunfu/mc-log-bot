package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.Field;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerJoinedDiscordLogHandlerTest extends BaseLogHandlerTest {

    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final String LOG_PLAYER_LEFT = "[23:35:23] [Server thread/INFO]: PLAYER001 left the game";
    private static final String FIELD_1_NAME = "FIELD_NAME_001";
    private static final String FIELD_1_VALUE = "FIELD_VALUE_001";
    private static final Field FIELD_1 = new Field(FIELD_1_NAME, FIELD_1_VALUE);
    private static final Response FEIGN_RESPONSE = buildFeignResponse();
    private final ParserResponse PARSER_RESPONSE = buildParserResponse();

    private PlayerJoinedDiscordLogHandler logHandler;
    private ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor = ArgumentCaptor.forClass(DiscordWebhookRequest.class);

    @BeforeEach
    void setUp() {
        logHandler = new PlayerJoinedDiscordLogHandler(discordClient, logParser, properties);
    }

    @Override
    protected ParserResponse buildParserResponse() {
        return new ParserResponse(buildParsedLogFields());
    }

    @Override
    protected Set<Field> buildParsedLogFields() {
        Set<Field> fields = new HashSet<>();
        fields.add(FIELD_1);
        return fields;
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

    @Override
    protected void verifyDiscordRequest(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor) {
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getAuthor().getName(), equalTo(AUTHOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getThumbnail().getUrl(), equalTo(THUMBNAIL_URL));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getTitle(), equalTo(TITLE));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getColor(), equalTo(COLOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getFields(), hasSize(1));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getFields(), containsInAnyOrder(FIELD_1));
        verifyTimestampFormat(discordReqCaptor);
    }
}