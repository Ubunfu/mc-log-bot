package com.github.ubunfu.service;

import com.github.ubunfu.handler.LogHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogScraperServiceTest {

    private static final String SAMPLE_LOG = "[00:00:00] Sample log!";
    private static final String LOG_PLAYER_JOINED = "[23:35:23] [Server thread/INFO]: PLAYER001 joined the game";
    private static final String LOG_PLAYER_LEFT = "[23:35:23] [Server thread/INFO]: PLAYER001 left the game";
    private static final String LOG_PLAYER_EARNED_ACHIEVEMENT =
            "[01:27:02] [Server thread/INFO]: PLAYER001 has made the advancement [Who's the Pillager Now?]";
    private static final String LOG_PLAYER_DIED = "";

    @Mock
    private LogHandler logHandler;

    private LogScraperService logScraperService;

    @BeforeEach
    void setUp() {
        Set<LogHandler> logHandlers = new HashSet<>();
        logHandlers.add(logHandler);
        this.logScraperService = new LogScraperService(logHandlers);
    }

    @Test
    void whenFilterMatchesExpectHandleLog() {
        when(logHandler.isMatch(anyString())).thenReturn(true);
        logScraperService.handleLog(SAMPLE_LOG);
        verify(logHandler, times(1)).isMatch(anyString());
        verify(logHandler, times(1)).handle(anyString());
    }

    @Test
    void whenFilterDoesNotMatchExpectDoNothing() {
        when(logHandler.isMatch(anyString())).thenReturn(false);
        logScraperService.handleLog(SAMPLE_LOG);
        verify(logHandler, times(1)).isMatch(anyString());
        verify(logHandler, never()).handle(anyString());
    }
}