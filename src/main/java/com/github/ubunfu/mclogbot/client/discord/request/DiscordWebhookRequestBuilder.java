package com.github.ubunfu.mclogbot.client.discord.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DiscordWebhookRequestBuilder {

    private DiscordWebhookRequest discordWebhookRequest;
    private ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_REQ_JSON = "{\"embeds\":[{\"author\":{\"name\":\"\"},\"thumbnail\":{\"url\":\"\"},\"title\":\"\",\"color\":0,\"fields\":[],\"timestamp\":\"\"}]}";
    private static final DateTimeFormatter DISCORD_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public DiscordWebhookRequestBuilder() {
        try {
            discordWebhookRequest = mapper.readValue(BASE_REQ_JSON, DiscordWebhookRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static DiscordWebhookRequestBuilder create() {
        return new DiscordWebhookRequestBuilder();
    }

    public DiscordWebhookRequestBuilder author(String author) {
        discordWebhookRequest.getEmbeds()[0].getAuthor().setName(author);
        return this;
    }

    public DiscordWebhookRequestBuilder thumbnailUrl(String url) {
        discordWebhookRequest.getEmbeds()[0].getThumbnail().setUrl(url);
        return this;
    }

    public DiscordWebhookRequestBuilder title(String title) {
        discordWebhookRequest.getEmbeds()[0].setTitle(title);
        return this;
    }

    public DiscordWebhookRequestBuilder color(long color) {
        discordWebhookRequest.getEmbeds()[0].setColor(color);
        return this;
    }

    public DiscordWebhookRequestBuilder fields(Set<Field> fields) {
        discordWebhookRequest.getEmbeds()[0].setFields(fields);
        return this;
    }

    public DiscordWebhookRequestBuilder timestamp(ZonedDateTime localDateTime) {
        discordWebhookRequest.getEmbeds()[0].setTimestamp(localDateTime.format(DISCORD_DATE_TIME_FORMATTER));
        return this;
    }

    public DiscordWebhookRequest build() {
        return discordWebhookRequest;
    }
}
