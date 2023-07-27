package io.github.cirosanchez.cmd;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

public class SpecialCommand implements SlashCommandCreateListener {
    
    SlashCommand cmd;
    
    public SpecialCommand(DiscordApi api){
        SlashCommand cmd = SlashCommand.with("mapis", "demuestra lo mucho q quiero a mapis").createForServer(api, 1027394040951488554L).join();
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        if (event.getSlashCommandInteraction().getCommandName().equals("mapis")){
            event.getInteraction().getChannel().get().sendMessage("Mapis es el amor de mi vida, sin mapis no soy nada, si mapis fuera una comida, ser\u00EDa el pan porq me encanta, si mapis es una persona ser\u00EDa nicki nicole pq le gusta, si mapis fuera un avion seria un airbus a320 xq me subo siempre, si mapis fuera un jugador de valorant ser\u00EDa keznitdeus pq pega más q un bate", null, true, null);
        }
    }
}
