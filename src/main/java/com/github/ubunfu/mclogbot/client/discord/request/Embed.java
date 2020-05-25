package com.github.ubunfu.mclogbot.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class Embed {

    @JsonProperty
    private Author author;

    @JsonProperty
    private Thumbnail thumbnail;

    @JsonProperty
    private String title;

    @JsonProperty
    private long color;

    @JsonProperty
    private Set<Field> fields;

    @JsonProperty
    private String timestamp;

}
