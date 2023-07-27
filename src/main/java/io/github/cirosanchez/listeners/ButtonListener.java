package io.github.cirosanchez.listeners;

import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.event.interaction.ButtonClickEvent;
import org.javacord.api.listener.interaction.ButtonClickListener;

public class ButtonListener implements ButtonClickListener {

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String customID = event.getButtonInteraction().getCustomId();
        System.out.println(customID);
        if (customID.equals("create_ticket")){
            ChannelCategory channelCategory = event.getInteraction().getServer().get().createChannelCategoryBuilder()
            .setName("Tickets")
            .addPermissionOverwrite(null, Permissions.fromBitmask(8)).create().join();


            event.getInteraction().getServer().get().createTextChannelBuilder().
            setCategory(channelCategory)
            .setName("ticket-"+event.getInteraction().getUser().getName())
            .create().join();
        }
    
    }
    
}
