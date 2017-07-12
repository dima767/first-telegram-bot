package org.example.dk.firsttelegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A Telegram polling bot
 */
public class FirstBot extends TelegramLongPollingBot {

    private String botToken;

    private String botUsername;

    private AtomicLong chatId = new AtomicLong(-1L);

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstBot.class);

    public FirstBot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        this.chatId.set(update.getMessage().getChatId());
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    public AtomicLong getChatId() {
        return chatId;
    }

    public boolean hasChatId() {
        return this.chatId.get() != -1L;
    }
}
