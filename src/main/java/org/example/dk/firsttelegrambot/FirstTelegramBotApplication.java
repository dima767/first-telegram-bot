package org.example.dk.firsttelegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

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
    public CommandLineRunner commandLineRunner(FirstBot bot) throws Exception {
        return args -> {
            while (true) {
                bot.getChatIdsCurrentlyConnectedToMe()
                        .forEach(chatId -> {
                            try {
                                bot.sendMessage(new SendMessage(chatId, "I am the simplest Telegram Robot 😎 Welcome to my world!"));
                            }
                            catch (TelegramApiException e) {
                                e.printStackTrace();
                                bot.removeMeFromChat(chatId);
                            }
                        });

                Thread.sleep(5000L);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FirstTelegramBotApplication.class, args);
    }
}
