package org.example.dk.firsttelegrambot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Config props for the bot.
 */
@ConfigurationProperties(prefix = "bot")
public class FirstBotConfigurationProperties {

    private String token;

    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
