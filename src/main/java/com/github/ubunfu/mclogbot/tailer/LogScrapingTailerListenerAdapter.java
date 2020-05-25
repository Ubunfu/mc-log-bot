package com.github.ubunfu.mclogbot.tailer;

import com.github.ubunfu.mclogbot.service.LogScraperService;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

import static java.lang.String.format;

@Component
public class LogScrapingTailerListenerAdapter extends TailerListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogScrapingTailerListenerAdapter.class);

    private LogScraperService logScraperService;

    @Autowired
    public LogScrapingTailerListenerAdapter(LogScraperService logScraperService) {
        this.logScraperService = logScraperService;
    }

    @Override
    public void init(Tailer tailer) {
        LOGGER.debug("Initialized the Tailer.");
    }

    @Override
    public void handle(String line) {
        LOGGER.info(format("Read event: %s", line));
        logScraperService.handleLog(line);
    }

    @Override
    public void fileNotFound() {
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
