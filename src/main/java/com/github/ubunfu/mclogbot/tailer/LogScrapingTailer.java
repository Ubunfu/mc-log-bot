package com.github.ubunfu.mclogbot.tailer;

import com.github.ubunfu.mclogbot.config.properties.TailerProperties;
import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LogScrapingTailer extends Tailer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogScrapingTailer.class);

    @Autowired
    public LogScrapingTailer(
            TailerProperties tailerProperties,
            LogScrapingTailerListenerAdapter tailerListenerAdapter) {
        super(
                new File(tailerProperties.getLogFile()),
                tailerListenerAdapter,
                tailerProperties.getReadDelayMillis(),
                tailerProperties.isReadFromEnd(),
                tailerProperties.isCloseFileBetweenChunks()
        );
        LOGGER.debug("Constructed Tailer.");
    }
}