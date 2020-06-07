package com.github.ubunfu.mclogbot.config.properties;

import lombok.Data;

@Data
public abstract class AbstractBotProperties implements BotProperties {

    private boolean enabled;
    private String author;
    private String title;
    private String thumbnailUrl;
    private long color;
}
