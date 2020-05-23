package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Field {

    @JsonProperty
    private String name;

    @JsonProperty
    private String value;
}
