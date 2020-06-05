# Minecraft Log Bot
[![CircleCI](https://circleci.com/gh/Ubunfu/mc-log-bot.svg?style=svg)](https://circleci.com/gh/Ubunfu/mc-log-bot)

Minecraft log bot is a collection of bots that scrape a Minecraft server log for events, and send messages to a webhook
endpoint when it finds them.

Compatible architectures: 
* linux/amd64
* linux/arm64

## What does it do?
* Post messages to a Discord channel when players join the Minecraft server

## How do I use it?
You have three primary options:
* Add this app to a new or existing Docker Compose YML file to manage it alongside your Minecraft server
* Pull this container and run it directly alongside your Minecraft server
* Clone this repository, build the container yourself, and run it alongside your Minecraft server

### Docker Compose (recommended)
Using a compose file makes it a lot easier to manage the configuration of the bots, bringing the container up and down,
 and allowing it to restart itself if it crashes for some reason. Use [docker-compose.yml](docker-compose.yml) as a 
 template to see how to set one up.
 
### Pull container from Docker Hub
1. Pull the container from Docker Hub
```bash
docker pull ubunfu/mc-log-bot
```

2. Run it alongside your Minecraft server
```bash
docker run -e APPS_DISCORD_HOOKURI=/path/of/discord/webhook -v /path/to/server/data:/data -d ubunfu/mc-log-bot
```

#### Parameters
* `-e APPS_DISCORD_HOOKURI=/path/of/discord/webhook`: Inject the URL path of a Discord webhook into the container
* `-v /path/to/server/data:/data`: Mount the Minecraft server data directory to the container filesystem @ `/data`.  
    This will give the container access to the server log files. See "Setting up the data directory" for more details.
* `-d`: Run the container in detached mode (not tied to current terminal session).  Use `docker logs <container-id>` to 
    view logs.
    
### Build and run from source
1. Clone this repository
```bash
git clone https://github.com/Ubunfu/mc-log-bot.git
cd mc-log-bot
```

2. Build the container
```bash
docker build -t mc-log-bot .
```

3. Run the container alongside your minecraft server (see above for parameter descriptions)
```bash
docker run -e APPS_DISCORD_HOOKURI=/path/of/discord/webhook -v /path/to/server/data:/data -d ubunfu/mc-log-bot
```

## Setting up the data directory
In order for the app to read your Minecraft server's log file, you have to mount the server's data directory to the 
container.  This should be the root directory of your minecraft server, probably the same place the JAR is located.

If you're running mc-log-bot via a Docker Compose file, you can configure this directory mount as shown in 
[docker-compose.yml](docker-compose.yml).

If you're running mc-log-bot manually using the `docker` command, configure the directory mount with the 
`-v "/server/data/:/data"` parameter.  

## Configuring the bots
The bots are highly configurable, but have reasonable defaults, so you don't need to set everything.  To set / override 
any of these, either add it to the `environment` field of your Docker Compose file as shown in 
[docker-compose.yml](docker-compose.yml), or toss in an extra `-e VAR=VALUE` param to the `docker run ...` command if 
you're running it that way.  

|Parameter | Description | Default | Required? |
|---       |---          | ---     | --- |
| TAILER_LOGFILE | Path to the log file from the container's perspective. | /data/logs/latest.log | No |
| TAILER_READDELAYMILLIS | How frequently the app checks the server log for updates. | 1000 | No |
| APPS_DISCORD_HOOKURI | The URI path of the Discord weboook to which the app should post notifications. | n/a | Yes |
| BOT_PLAYER_JOINED_ENABLED | Turns the player-joined bot on or off | true | No |
| BOT_PLAYER_JOINED_AUTHOR | The author field that will appear in the notification tile displayed in Discord | Attendance Bot | No |
| BOT_PLAYER_JOINED_TITLE |  The title message of the notification tile that will be displayed in Discord | Player joined the server! | No |
| BOT_PLAYER_JOINED_THUMBNAILURL | The URL of the image thumbnail to embed within the notification tile displayed in Discord | https://mc-log-bot-assets.s3.us-east-2.amazonaws.com/pickaxe.png | No |
| BOT_PLAYER_JOINED_COLOR | The color of the notification tile displayed in Discord. ***Represented by converting a hex color-code into decimal***. | 65280 | No |