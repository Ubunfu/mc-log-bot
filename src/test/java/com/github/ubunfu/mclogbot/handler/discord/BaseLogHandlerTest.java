package com.github.ubunfu.mclogbot.handler.discord;

import com.github.ubunfu.mclogbot.client.discord.DiscordClient;
import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.client.discord.request.Field;
import com.github.ubunfu.mclogbot.config.properties.BotProperties;
import com.github.ubunfu.mclogbot.parser.LogParser;
import com.github.ubunfu.mclogbot.parser.ParserResponse;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class BaseLogHandlerTest {

    protected static final String AUTHOR = "AUTHOR_001";
    protected static final String THUMBNAIL_URL = "THUMBNAIL_001";
    protected static final String TITLE = "TITLE_001";
    protected static final long COLOR = 1;

    @Mock
    protected BotProperties properties;

    @Mock
    protected LogParser logParser;

    @Mock
    protected DiscordClient discordClient;

    protected static Response buildFeignResponse() {
        return Response.builder()
                .request(buildFeignRequest())
                .build();
    }

    protected static Request buildFeignRequest() {
        return Request.create(
                Request.HttpMethod.POST,
                "http://localhost",
                new HashMap<>(),
                Request.Body.empty(),
                null);
    }

    protected void verifyTimestampFormat(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor) {
        String timestampString = discordReqCaptor.getValue().getEmbeds()[0].getTimestamp();
        ZonedDateTime.parse(timestampString);
    }

    protected void configureMockProperties() {
        when(properties.getAuthor()).thenReturn(AUTHOR);
        when(properties.getThumbnailUrl()).thenReturn(THUMBNAIL_URL);
        when(properties.getTitle()).thenReturn(TITLE);
        when(properties.getColor()).thenReturn(COLOR);
    }

    protected abstract ParserResponse buildParserResponse();

    protected abstract Set<Field> buildParsedLogFields();

    protected abstract void verifyDiscordRequest(ArgumentCaptor<DiscordWebhookRequest> discordReqCaptor);
}
