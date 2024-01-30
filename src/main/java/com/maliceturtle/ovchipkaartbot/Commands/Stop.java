package com.maliceturtle.ovchipkaartbot.Commands;

import com.maliceturtle.ovchipkaartbot.Icommand;
import com.maliceturtle.ovchipkaartbot.lavaplayer.GuildMusicManager;
import com.maliceturtle.ovchipkaartbot.lavaplayer.PlayerManager;
import com.maliceturtle.ovchipkaartbot.lavaplayer.TrackScheduler;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Stop implements Icommand {
    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "clears queue and stops playing music";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member=event.getMember();
        GuildVoiceState membervoiceState=member.getVoiceState();
        if(!membervoiceState.inAudioChannel()){
            event.reply("You need to be in voice channel to execute this command").queue();
            return;
        }
        Member self=event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState=self.getVoiceState();
        if(!selfVoiceState.inAudioChannel()){
            event.reply("Bot is not in the voice channel currently" ).queue();
        }
        if(selfVoiceState.getChannel()!=membervoiceState.getChannel()){
            event.reply("You need to be in the same voice channel to be able to execute this command").queue();

        }
        GuildMusicManager guildMusicManager= PlayerManager.get().getGuildMusicManager(event.getGuild());
        TrackScheduler trackScheduler=guildMusicManager.getTrackScheduler();
        trackScheduler.getQueue().clear();
        trackScheduler.getPlayer().stopTrack();
        event.reply("Stopping current track").queue();




    }
}
