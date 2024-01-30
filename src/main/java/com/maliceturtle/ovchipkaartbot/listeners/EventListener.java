package com.maliceturtle.ovchipkaartbot.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
     User user= event.getUser();
     String emoji=event.getReaction().getEmoji().getAsReactionCode();

        String channel=event.getChannel().getAsMention();

        String jmp=event.getJumpUrl();
        String msg=user.getEffectiveName()+ " reacted to a message with "+emoji+ " in the " + channel+ " channel!";
        TextChannel textChannel=event.getGuild().getTextChannelsByName("bot-testing",true).get(0);

        textChannel.sendMessage(msg).queue();
    //    List<GuildChannel> channels=event.getGuild().getChannels();
//        channels.stream()
//                .forEach(System.out::println);

    }
}
