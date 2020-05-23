package com.github.ubunfu.mclogbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tile.playerJoined")
public class PlayerJoinedTileProperties {

    private String author;
    private String title;
    private String thumbnailUrl;
    private long color;
}
