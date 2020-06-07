package com.github.ubunfu.mclogbot.config.properties;

public interface BotProperties {

    boolean isEnabled();
    void setEnabled(boolean enabled);

    String getAuthor();
    void setAuthor(String author);

    String getTitle();
    void setTitle(String title);

    String getThumbnailUrl();
    void setThumbnailUrl(String thumbnailUrl);

    long getColor();
    void setColor(long color);
}
