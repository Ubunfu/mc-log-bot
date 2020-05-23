package com.github.ubunfu.client.discord.request;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class DiscordWebookRequestBuilderTest {

    private static final String ANY_STRING = "STRING";
    private static final long ANY_LONG = 1;
    private static final Set<Field> ANY_FIELD_SET = new HashSet<>();
    private static final Date ANY_DATE = new Date();

    @Test
    void expectBuilderAlwaysReturnsBuilder() {
        assertThat(DiscordWebhookRequestBuilder.create(), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().author(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().title(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().color(ANY_LONG), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().thumbnailUrl(ANY_STRING), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().fields(ANY_FIELD_SET), instanceOf(DiscordWebhookRequestBuilder.class));
        assertThat(DiscordWebhookRequestBuilder.create().timestamp(ANY_DATE), instanceOf(DiscordWebhookRequestBuilder.class));
    }

    @Test
    void expectBuildReturnsDiscordWebhookRequest() {
        assertThat(DiscordWebhookRequestBuilder.create().build(), instanceOf(DiscordWebhookRequest.class));
    }
}
