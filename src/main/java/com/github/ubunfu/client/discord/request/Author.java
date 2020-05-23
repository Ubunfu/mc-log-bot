package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Author {

    @JsonProperty
    private String name;
}