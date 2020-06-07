package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@ConditionalOnProperty(name = "bot.achievement.enabled", havingValue = "true")
public class AchievementLogParser extends AbstractLogParser {
    public ParserResponse parse(String logMessage) {
        fields.clear();
        fields.add(new Field("Player", getPlayerName(logMessage)));
        fields.add(new Field("Achievement", getAchievement(logMessage)));
        return new ParserResponse(fields);
    }

    private String getPlayerName(String logMessage) {
        return logMessage.split(" ")[3];
    }

    private String getAchievement(String logMessage) {
        int achievementStart = logMessage.lastIndexOf("[") + 1;
        int achievementEnd = logMessage.lastIndexOf("]");
        return logMessage.substring(achievementStart, achievementEnd);
    }
}
