package org.example.dk.firsttelegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.api.methods.send.SendMessage;

@SpringBootApplication
@EnableConfigurationProperties(FirstBotConfigurationProperties.class)
public class FirstTelegramBotApplication {

	@Autowired
	private FirstBotConfigurationProperties firstBotConfigurationProperties;

	@Bean
	public FirstBot firstBot() {
		return new FirstBot(this.firstBotConfigurationProperties.getToken(), this.firstBotConfigurationProperties.getUsername());
	}

	@Bean
	public CommandLineRunner commandLineRunner(FirstBot bot) {
	    return args -> {
            while(true) {
                if(!bot.hasChatId()) {
                    continue;
                }
                bot.sendMessage(new SendMessage(bot.getChatId().get(), "Test message"));
                Thread.sleep(2000L);
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(FirstTelegramBotApplication.class, args);
	}
}
