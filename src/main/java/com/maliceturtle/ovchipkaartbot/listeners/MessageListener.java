package com.maliceturtle.ovchipkaartbot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user=event.getAuthor();
        Message message=event.getMessage();
        TextChannel textChannel=event.getGuild().getTextChannelsByName("bot-testing",true).get(0);
        textChannel.addReactionById(event.getMessageId(), Emoji.fromFormatted("U+1F60D")).queue();
    }
}
