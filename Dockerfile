FROM maven:3-openjdk-11-slim

WORKDIR /app
COPY . /app

ENV TAILER_LOGFILE /data/logs/latest.log
ENV TAILER_READDELAYMILLIS 1000
ENV APPS_DISCORD_HOOKURI /api/webhooks/HOOK/PATH
ENV BOT_PLAYER_JOINED_AUTHOR Attendance Bot
ENV BOT_PLAYER_JOINED_TITLE Player joined the server!
ENV BOT_PLAYER_JOINED_THUMBNAILURL https://mc-log-bot-assets.s3.us-east-2.amazonaws.com/pickaxe.png
ENV BOT_PLAYER_JOINED_COLOR 65280

RUN ["mvn", "clean", "package", "-DskipTests"]

ENTRYPOINT ["mvn", "spring-boot:run"]