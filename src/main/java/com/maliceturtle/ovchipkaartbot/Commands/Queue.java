package com.maliceturtle.ovchipkaartbot.Commands;

import com.maliceturtle.ovchipkaartbot.Icommand;
import com.maliceturtle.ovchipkaartbot.lavaplayer.GuildMusicManager;
import com.maliceturtle.ovchipkaartbot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Queue implements Icommand {
    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getDescription() {
        return "will display current queue";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            event.reply("You need to be in a voice channel").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()) {
            event.reply("I am not in an audio channel").queue();
            return;
        }

        if(selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
            event.reply("You are not in the same channel as me").queue();
            return;
        }
        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        List<AudioTrack> queue = new ArrayList<>(guildMusicManager.getTrackScheduler().getQueue());
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Current queue");
        if(queue.isEmpty()){
            embedBuilder.setDescription("Queue is empty");

        }
        for(int i=0;i<queue.size();i++){
            AudioTrackInfo info=queue.get(i).getInfo();
            embedBuilder.addField(i+1 + ":",info.title,false);

        }
        event.replyEmbeds(embedBuilder.build()).queue();


    }
}
