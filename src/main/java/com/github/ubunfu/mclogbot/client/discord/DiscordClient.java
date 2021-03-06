package com.github.ubunfu.mclogbot.client.discord;

import com.github.ubunfu.mclogbot.client.discord.request.DiscordWebhookRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "discordWebhook", url = "${apps.discord.hookHost}")
public interface DiscordClient {

    @PostMapping(
            path = "${apps.discord.hookUri}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    Response invokeWebhook(@RequestBody DiscordWebhookRequest request);
}