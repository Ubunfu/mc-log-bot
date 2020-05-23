package com.github.ubunfu.mclogbot;

import com.github.ubunfu.mclogbot.LogScrapingTailerListenerAdapter;
import org.apache.commons.io.input.Tailer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TailerTest {

    private static final String LOG_PATH = "testLog2.log";
    private static final File TEST_FILE = new File(LOG_PATH);
    private static final String TEST_LINE = "TEST LOG MESSAGE";
    private static final long READ_DELAY_MILLIS = 1000;

    @Mock
    private LogScrapingTailerListenerAdapter logScrapingTailerListenerAdapter;

    @BeforeEach
    public void setUp() throws IOException {
        createTestFile(LOG_PATH);
        Tailer.create(
                TEST_FILE,
                logScrapingTailerListenerAdapter,
                READ_DELAY_MILLIS,
                true,
                true);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(getPath(LOG_PATH));
    }

    @Test
    public void expectTailerCallsHandler() throws IOException, InterruptedException {
        assertThat(TEST_FILE.canRead(), is(true));
        assertThat(TEST_FILE.canWrite(), is(true));
        writeLine(LOG_PATH, TEST_LINE);
        Thread.sleep(READ_DELAY_MILLIS);
        verify(logScrapingTailerListenerAdapter, times(1))
                .handle(anyString());
    }

    private void createTestFile(String logPath) throws IOException {
        Files.write(getPath(logPath), "".getBytes(), StandardOpenOption.CREATE);
    }

    private Path getPath(String logPath) {
        return Paths.get(logPath);
    }

    private void writeLine(String filePath, String message) throws IOException {
        byte[] logLine = (message + "\n").getBytes();
        Files.write(getPath(filePath), logLine, StandardOpenOption.WRITE);
    }
}
