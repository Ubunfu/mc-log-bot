package com.github.ubunfu.mclogbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tile.player-joined")
public class PlayerJoinedTileProperties {

    private String author;
    private String title;
    private String thumbnailUrl;
    private long color;
}
