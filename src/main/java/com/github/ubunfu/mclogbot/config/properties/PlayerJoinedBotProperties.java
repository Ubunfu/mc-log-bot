package com.github.ubunfu.mclogbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bot.player-joined")
public class PlayerJoinedBotProperties {

    private String author;
    private String title;
    private String thumbnailUrl;
    private long color;
}
