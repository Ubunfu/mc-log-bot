FROM maven:3-openjdk-11-slim

WORKDIR /app
COPY . /app

ENV TAILER_LOGFILE /data/logs/latest.log
ENV TAILER_READDELAYMILLIS 1000
ENV APPS_DISCORD_HOOKURI /api/webhooks/HOOK/PATH

ENV BOT_PLAYER_JOINED_ENABLED true
ENV BOT_PLAYER_JOINED_AUTHOR Attendance Bot
ENV BOT_PLAYER_JOINED_TITLE Player joined the server!
ENV BOT_PLAYER_JOINED_THUMBNAILURL https://mc-log-bot-assets.s3.us-east-2.amazonaws.com/pickaxe.png
ENV BOT_PLAYER_JOINED_COLOR 65280

ENV BOT_ACHIEVEMENT_ENABLED true
ENV BOT_ACHIEVEMENT_AUTHOR Achievement Bot
ENV BOT_ACHIEVEMENT_TITLE Somebody earned an achievement!
ENV BOT_ACHIEVEMENT_THUMBNAILURL https://mc-log-bot-assets.s3.us-east-2.amazonaws.com/trophy.png
ENV BOT_ACHIEVEMENT_COLOR 16766720

ENV BOT_DEATH_ENABLED true
ENV BOT_DEATH_AUTHOR Death Bot
ENV BOT_DEATH_THUMBNAILURL https://mc-log-bot-assets.s3.us-east-2.amazonaws.com/reaper.png
ENV BOT_DEATH_COLOR 9109504

RUN ["mvn", "clean", "package", "-DskipTests"]

ENTRYPOINT ["mvn", "spring-boot:run"]