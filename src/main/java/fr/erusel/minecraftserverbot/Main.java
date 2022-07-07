package fr.erusel.minecraftserverbot;

import fr.erusel.minecraftserverbot.exceptions.InvalidTokenException;
import fr.erusel.minecraftserverbot.listeners.SlashCommandListener;
import fr.erusel.minecraftserverbot.managers.FileManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Collections;

import static net.dv8tion.jda.api.requests.GatewayIntent.DIRECT_MESSAGE_REACTIONS;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGE_REACTIONS;


public class Main {


    public static void main(String[] args){

        // OTkzOTMxMDUxNjM2Mzc5Nzcw.GH9lPk.8CNeXOZRvyfov0VmnCu5ZpXp7a48hcGUr-yTeQ
        // ID : 993931051636379770

        FileManager fileManager = new FileManager();

        JDA jda;

        // Connection to the bot
        try {
            jda = JDABuilder.createLight(fileManager.getToken(), Collections.emptyList())
                    .setActivity(Activity.playing("Checking Minecraft servers..."))
                    .addEventListeners(new SlashCommandListener(fileManager))
                    .build();
        } catch (LoginException e) {
            try {
                throw new InvalidTokenException("Invalid Token");
            } catch (InvalidTokenException ex) {
                throw new RuntimeException(ex);
            }
        }

        jda.upsertCommand("setup", "Setup the bot")
                .queue();

        jda.updateCommands();



    }





}
