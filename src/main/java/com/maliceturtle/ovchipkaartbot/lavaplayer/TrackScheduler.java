package com.maliceturtle.ovchipkaartbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler  extends AudioEventAdapter {
    private AudioPlayer player;
    private BlockingQueue<AudioTrack> queue=new LinkedBlockingQueue<>();

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        player.startTrack(queue.poll(),false);


    }
    public void  queue(AudioTrack track){
        if(!player.startTrack(track,true)){
            queue.offer(track);


        }

    }
}
