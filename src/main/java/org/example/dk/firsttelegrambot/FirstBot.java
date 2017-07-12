package org.example.dk.firsttelegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * A Telegram polling bot
 */
public class FirstBot extends TelegramLongPollingBot {

    private String botToken;

    private String botUsername;

    private AtomicLong chatId = new AtomicLong(-1L);

    private Queue<Long> chatIds = new ConcurrentLinkedQueue<>();

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
        LOGGER.info("Received a chat bot update from Telegram servers: {}", update);
        this.chatIds.offer(update.getMessage().getChatId());
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    public Stream<Long> getChatIdsCurrentlyConnectedToMe() {
        return this.chatIds.stream();
    }

    public void removeMeFromChat(Long chatId) {
        this.chatIds.remove(chatId);
    }
}
