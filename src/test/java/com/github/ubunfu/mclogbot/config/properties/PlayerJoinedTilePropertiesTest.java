package com.github.ubunfu.mclogbot.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(properties = {
        "tile.playerJoined.author=AUTHOR",
        "tile.playerJoined.title=TITLE",
        "tile.playerJoined.color=1",
        "tile.playerJoined.ThumbnailUrl=THUMBNAIL_URL"
})
public class PlayerJoinedTilePropertiesTest {

    @Autowired
    private PlayerJoinedTileProperties properties;

    @Test
    void expectPropertiesParsedCorrectly() {
        assertThat(properties.getAuthor(), equalTo("AUTHOR"));
        assertThat(properties.getTitle(), equalTo("TITLE"));
        assertThat(properties.getColor(), equalTo(1L));
        assertThat(properties.getThumbnailUrl(), equalTo("THUMBNAIL_URL"));
    }
}
