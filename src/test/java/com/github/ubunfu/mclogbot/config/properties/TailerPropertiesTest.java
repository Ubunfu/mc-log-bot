package com.github.ubunfu.mclogbot.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(properties = {
        "tailer.logFile=LOG_FILE",
        "tailer.readDelayMillis=1",
        "tailer.readFromEnd=true",
        "tailer.closeFileBetweenChunks=true"
})
public class TailerPropertiesTest {

    @Autowired
    private TailerProperties properties;

    @Test
    void expectPropertiesReadCorrectly() {
        assertThat(properties.getLogFile(), equalTo("LOG_FILE"));
        assertThat(properties.getReadDelayMillis(), equalTo(1L));
        assertThat(properties.isReadFromEnd(), equalTo(true));
        assertThat(properties.isCloseFileBetweenChunks(), equalTo(true));
    }
}