package com.maliceturtle.ovchipkaartbot.Commands;

import com.maliceturtle.ovchipkaartbot.Icommand;
import com.maliceturtle.ovchipkaartbot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Play  implements Icommand {
    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "will play a song";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options=new ArrayList<>();
        options.add(new OptionData(OptionType.STRING,"name"," Name of the song to play",true));
        return options;

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
            event.getGuild().getAudioManager().openAudioConnection(membervoiceState.getChannel());

        }
        else if(selfVoiceState.getChannel()!=membervoiceState.getChannel()){
            event.reply("You need to be in the same voice channel as bot").queue();


        }
        PlayerManager playerManager=PlayerManager.get();
        event.reply("Playing").queue();
        playerManager.play(event.getGuild(),event.getOption("name").getAsString());



    }
}
