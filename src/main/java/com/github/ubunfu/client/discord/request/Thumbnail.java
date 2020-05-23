package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnail {

    @JsonProperty
    private String url;
}
