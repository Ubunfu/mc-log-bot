package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class DiscordWebhookRequestBuilder {

    private DiscordWebhookRequest discordWebhookRequest;
    private ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_REQ_JSON = "{\"embeds\":[{\"author\":{\"name\":\"\"},\"thumbnail\":{\"url\":\"\"},\"title\":\"\",\"color\":0,\"fields\":[],\"timestamp\":\"\"}]}";

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

    public DiscordWebhookRequestBuilder timestamp(String timestamp) {
        discordWebhookRequest.getEmbeds()[0].setTimestamp(timestamp);
        return this;
    }

    public DiscordWebhookRequest build() {
        return discordWebhookRequest;
    }
}
