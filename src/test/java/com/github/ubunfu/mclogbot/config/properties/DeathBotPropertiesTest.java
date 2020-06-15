package com.github.ubunfu.mclogbot.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(properties = {
        "bot.death.enabled=true",
        "bot.death.author=AUTHOR",
        "bot.death.color=1",
        "bot.death.ThumbnailUrl=THUMBNAIL_URL"
})
public class DeathBotPropertiesTest {

    @Autowired
    private DeathBotProperties properties;

    @Test
    void expectPropertiesParsedCorrectly() {
        assertThat(properties.isEnabled(), equalTo(true));
        assertThat(properties.getAuthor(), equalTo("AUTHOR"));
        assertThat(properties.getTitle(), equalTo(null));
        assertThat(properties.getColor(), equalTo(1L));
        assertThat(properties.getThumbnailUrl(), equalTo("THUMBNAIL_URL"));
    }
}
