package com.github.ubunfu.mclogbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tailer")
public class TailerProperties {

    private String logFile;
    private long readDelayMillis;
    private boolean readFromEnd;
    private boolean closeFileBetweenChunks;
}
