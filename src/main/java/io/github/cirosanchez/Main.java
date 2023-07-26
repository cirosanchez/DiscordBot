package io.github.cirosanchez;


import io.github.cirosanchez.listeners.MessageListener;
import io.github.cirosanchez.token.TokenJSON;

import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        DiscordApi api = null;


        try {
            api = new DiscordApiBuilder().setToken(TokenJSON.getToken()).setAllIntents().login().join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        api.updateActivity(ActivityType.PLAYING, "bot tu hermana");
        System.out.println("Bot connected: "+api.getYourself().getName());
        System.out.print("Servers: ");
        for (Server server : api.getServers()){
            System.out.print(server.getName());
        }
        System.out.println(" ");
        System.out.println(" Invite: " +api.createBotInvite());
        api.addListener(new MessageListener());

    }
}