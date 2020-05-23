package com.github.ubunfu.mclogbot;

import com.github.ubunfu.mclogbot.service.LogScraperService;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LogScrapingTailerListenerAdapter extends TailerListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogScrapingTailerListenerAdapter.class);

    @Autowired
    LogScraperService logScraperService;

    @Override
    public void handle(String line) {
    }
}
