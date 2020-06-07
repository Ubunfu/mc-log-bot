package com.github.ubunfu.mclogbot.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(properties = {
        "bot.achievement.enabled=true",
        "bot.achievement.author=AUTHOR",
        "bot.achievement.title=TITLE",
        "bot.achievement.color=1",
        "bot.achievement.ThumbnailUrl=THUMBNAIL_URL"
})
public class AchievementBotPropertiesTest {

    @Autowired
    private AchievementBotProperties properties;

    @Test
    void expectPropertiesParsedCorrectly() {
        assertThat(properties.isEnabled(), equalTo(true));
        assertThat(properties.getAuthor(), equalTo("AUTHOR"));
        assertThat(properties.getTitle(), equalTo("TITLE"));
        assertThat(properties.getColor(), equalTo(1L));
        assertThat(properties.getThumbnailUrl(), equalTo("THUMBNAIL_URL"));
    }
}
