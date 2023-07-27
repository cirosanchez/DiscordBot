package io.github.cirosanchez.cmd;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.Action;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.ButtonBuilder;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.message.mention.AllowedMentions;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.GloballyAttachableListener;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;
import org.javacord.api.listener.message.MessageCreateListener;

import io.github.cirosanchez.util.PermissionChecker;



public class TicketCommand implements SlashCommandCreateListener {
    SlashCommand cmd;
    static DiscordApi api;
    
    public TicketCommand(DiscordApi api){
        SlashCommand cmd = SlashCommand.with("send-ticket", "Sends ticket module.").createGlobal(api).join();
        this.api = api;
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        if (event.getSlashCommandInteraction().getCommandName().equals("send-ticket")){
            if (PermissionChecker.hasPermissionInServer(event.getSlashCommandInteraction().getUser(), event.getSlashCommandInteraction().getServer().get(), PermissionType.MANAGE_CHANNELS)){
                event.getSlashCommandInteraction().createImmediateResponder()
                .setContent("Sending ticket module...")
                .setFlags(MessageFlag.EPHEMERAL) // Make the message ephemeral
                .respond();

            EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Ticket module")
                .setDescription("Click the button below to create a ticket.")
                .setFooter("Created by " + event.getSlashCommandInteraction().getUser().getDiscriminatedName())
                .setTimestampToNow();

                // Create a button for ticket creation
                ButtonBuilder buttonBuilder = new ButtonBuilder()
                .setStyle(ButtonStyle.PRIMARY) // Button style, you can choose other styles if needed
                .setLabel("Create Ticket")
                .setEmoji("🎫"); // Optional: You can use an emoji for the button label

                // Assign a custom ID to the button to identify it when it's clicked
                // This is important as it will be used in the "onButtonClick" method to handle the click event.
                buttonBuilder.setCustomId("create_ticket");

                // Create an action row and add the button to it
                ActionRow actionRow = ActionRow.of(buttonBuilder.build());

                // Send the embed message with the button to everyone
                event.getInteraction().getChannel().get().asServerTextChannel().get()
                    .sendMessage(embed, actionRow) // Add the action row containing the button to the message
                    .join();
            } 
        }
    }


    
}