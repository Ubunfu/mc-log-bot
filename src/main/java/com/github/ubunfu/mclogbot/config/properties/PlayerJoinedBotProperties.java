package com.github.ubunfu.mclogbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot.player-joined")
public class PlayerJoinedBotProperties extends AbstractBotProperties { }
