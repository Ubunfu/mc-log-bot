package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordWebhookRequest {

    @JsonProperty
    private Embed[] embeds;

    private class Embed {

        @JsonProperty
        private Author author;

        @JsonProperty
        private Thumbnail thumbnail;

        @JsonProperty
        private String title;

        @JsonProperty
        private String color;

        @JsonProperty
        private Field[] fields;

        @JsonProperty
        private String timestamp;

        private class Author {

            @JsonProperty
            private String name;
        }

        private class Thumbnail {

            @JsonProperty
            private String url;
        }

        private class Field {

            @JsonProperty
            private String name;

            @JsonProperty
            private String value;
        }
    }
}

