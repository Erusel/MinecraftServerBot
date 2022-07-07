package fr.erusel.minecraftserverbot.listeners;

import fr.erusel.minecraftserverbot.managers.FileManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    FileManager fileManager;

    public SlashCommandListener(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("setup")) {

            if (!fileManager.isSetup()){
                event.reply("Mise en place du bot").setEphemeral(true).queue();
                Guild guild = event.getGuild();

                guild.createCategory("Server").queue();
                guild.createTextChannel("Minecraft Server").queue();











            }else {
                event.reply("Le bot a dejà été setup").setEphemeral(true).queue();
            }



        }

    }
}
