package com.github.ubunfu.client.discord;

import com.github.ubunfu.client.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.client.discord.response.DiscordWebhookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "discordWebhook", url = "${webhooks.discord.host}")
public interface DiscordWebhookClient {

    @PostMapping(
            path = "${webhooks.discord.uri}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    DiscordWebhookResponse invokeWebhook(@RequestBody DiscordWebhookRequest request);
}
