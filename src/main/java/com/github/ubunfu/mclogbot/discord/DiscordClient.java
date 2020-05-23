package com.github.ubunfu.mclogbot.discord;

import com.github.ubunfu.mclogbot.discord.request.DiscordWebhookRequest;
import com.github.ubunfu.mclogbot.discord.response.DiscordWebhookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "discordWebhook", url = "${webhooks.discord.host}")
public interface DiscordClient {

    @PostMapping(
            path = "${webhooks.discord.uri}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    DiscordWebhookResponse invokeWebhook(@RequestBody DiscordWebhookRequest request);
}