package com.github.ubunfu.mclogbot;

import com.github.ubunfu.mclogbot.tailer.LogScrapingTailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MinecraftServerLogBotApplication implements CommandLineRunner {

	@Autowired
	private LogScrapingTailer tailer;

	@Value("${tailer.enabled}")
	private boolean tailerEnabled;

	public static void main(String[] args) {
		SpringApplication.run(MinecraftServerLogBotApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (tailerEnabled)
			tailer.run();
	}
}
