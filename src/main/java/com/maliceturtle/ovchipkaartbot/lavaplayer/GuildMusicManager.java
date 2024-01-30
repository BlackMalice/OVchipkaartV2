package com.maliceturtle.ovchipkaartbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    private TrackScheduler trackScheduler;
    private AudioForwader audioForwader;
    public GuildMusicManager(AudioPlayerManager manager){
        AudioPlayer player=manager.createPlayer();
        trackScheduler=new TrackScheduler(player);
        player.addListener(trackScheduler);
        audioForwader=new AudioForwader(player);


    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    public AudioForwader getAudioForwader() {
        return audioForwader;
    }
}
