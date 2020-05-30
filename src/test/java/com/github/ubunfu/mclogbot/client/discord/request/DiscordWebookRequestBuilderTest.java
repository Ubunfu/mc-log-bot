package com.github.ubunfu.mclogbot.client.discord.request;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class DiscordWebookRequestBuilderTest {

    private static final String ANY_STRING = "STRING";
    private static final long ANY_LONG = 1;
    private static final Set<Field> ANY_FIELD_SET = new HashSet<>();
    private static final ZonedDateTime ANY_ZONED_DATE_TIME = ZonedDateTime.now();

    @Test
    void expectBuilderAlwaysReturnsBuilder() {
        assertThat(DiscordWebhookRequestBuilder.create(), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().author(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().title(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().color(ANY_LONG), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().thumbnailUrl(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().fields(ANY_FIELD_SET), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().timestamp(ANY_ZONED_DATE_TIME), instanceOf(DiscordWebhookRequestBuilder.class));
    }

    @Test
    void expectBuildReturnsDiscordWebhookRequest() {
        assertThat(DiscordWebhookRequestBuilder.create().build(), instanceOf(DiscordWebhookRequest.class));
    }

    @Test
    void expectBuiltRequestFieldsAreCorrect() {
        DiscordWebhookRequest request = DiscordWebhookRequestBuilder.create()
                .author(ANY_STRING)
                .title(ANY_STRING)
                .color(ANY_LONG)
                .thumbnailUrl(ANY_STRING)
                .fields(ANY_FIELD_SET)
                .timestamp(ANY_ZONED_DATE_TIME)
                .build();
        Embed requestMeat = request.getEmbeds()[0];
        assertThat(requestMeat.getAuthor().getName(), equalTo(ANY_STRING));
        assertThat(requestMeat.getTitle(), equalTo(ANY_STRING));
        assertThat(requestMeat.getColor(), equalTo(ANY_LONG));
        assertThat(requestMeat.getThumbnail().getUrl(), equalTo(ANY_STRING));
        assertThat(requestMeat.getFields(), equalTo(ANY_FIELD_SET));
        assertThat(requestMeat.getTimestamp(), equalTo(ANY_ZONED_DATE_TIME.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }
}
