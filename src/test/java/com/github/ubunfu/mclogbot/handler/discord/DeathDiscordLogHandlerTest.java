package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.Field;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class DeathDiscordLogHandlerTest extends BaseLogHandlerTest {

    private static final String TITLE = "PLAYER001 was shot by Arrow";
    private static final String LOG_PLAYER_DIED = "[23:35:23] [Server thread/INFO]: PLAYER001 was shot by Arrow";
    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final Response FEIGN_RESPONSE = buildFeignResponse();

    private DeathDiscordLogHandler deathDiscordLogHandler;

    @BeforeEach
    void setUp() {
        deathDiscordLogHandler = new DeathDiscordLogHandler(discordClient, logParser, properties);
    }

    @Test
    void whenPlayerDiesExpectLogHandlerMatches() {
        assertThat(deathDiscordLogHandler.isMatch(LOG_PLAYER_DIED), is(true));
    }

    @Test
    void whenPlayerDidNotDieLogHandlerDoesNotMatch() {
        assertThat(deathDiscordLogHandler.isMatch(LOG_PLAYER_JOINED), is(false));
    }

    @Test
    void whenHandleLogExpectParseLogAndCallDiscord() {
        when(discordClient.invokeWebhook(any()))
                .thenReturn(FEIGN_RESPONSE);
        configureMockProperties();
        deathDiscordLogHandler.handle(LOG_PLAYER_DIED);
        verify(discordClient, times(1)).invokeWebhook(discordReqCaptor.capture());
        verifyDiscordRequest(discordReqCaptor);
    }

    @Override
    protected ParserResponse buildParserResponse() {
        return null;
    }

    @Override
    protected Set<Field> buildParsedLogFields() {
        return null;
    }

    @Override
    protected void verifyDiscordRequest(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor) {
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getAuthor().getName(), equalTo(AUTHOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getThumbnail().getUrl(), equalTo(THUMBNAIL_URL));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getTitle(), equalTo(TITLE));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getColor(), equalTo(COLOR));
        assertThat(discordReqCaptor.getValue().getEmbeds()[0].getFields(), hasSize(0));
        verifyTimestampFormat(discordReqCaptor);
    }
}
