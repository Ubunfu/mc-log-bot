package com.github.ubunfu.mclogbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot.death")
public class DeathBotProperties extends AbstractBotProperties {
}
