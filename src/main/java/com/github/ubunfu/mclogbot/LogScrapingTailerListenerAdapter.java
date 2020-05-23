package com.github.ubunfu.mclogbot;

import com.github.ubunfu.mclogbot.service.LogScraperService;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogScrapingTailerListenerAdapter extends TailerListenerAdapter {

    private LogScraperService logScraperService;

    @Autowired
    public LogScrapingTailerListenerAdapter(LogScraperService logScraperService) {
        this.logScraperService = logScraperService;
    }

    @Override
    public void handle(String line) {
        logScraperService.handleLog(line);
    }
}
