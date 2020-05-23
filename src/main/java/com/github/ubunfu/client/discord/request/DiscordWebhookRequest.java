package com.github.ubunfu.client.discord.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiscordWebhookRequest {

    @JsonProperty
    private Embed[] embeds;
}

