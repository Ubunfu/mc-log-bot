package com.github.ubunfu.mclogbot.tailer;

import com.github.ubunfu.mclogbot.service.LogScraperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LogScrapingTailerListenerAdapterTest {

    private LogScrapingTailerListenerAdapter listenerAdapter;

    @Mock
    private LogScraperService logScraperService;

    @BeforeEach
    void setUp() {
        listenerAdapter = new LogScrapingTailerListenerAdapter(logScraperService);
    }

    @Test
    void whenListenerAdapterHandlesLogExpectScraperServiceHandlesLog() {
        listenerAdapter.handle(anyString());
        verify(logScraperService, times(1)).handleLog(anyString());
    }
}
