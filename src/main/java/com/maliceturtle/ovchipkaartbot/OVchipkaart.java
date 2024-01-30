package com.maliceturtle.ovchipkaartbot;

import com.maliceturtle.ovchipkaartbot.listeners.MessageListener;
import com.maliceturtle.ovchipkaartbot.Commands.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class OVchipkaart {
    private final ShardManager shardManager;
    private final Dotenv config;

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig() {
        return config;
    }

    public  OVchipkaart() throws LoginException {
        config=Dotenv.configure().load();
        String token=config.get("TOKEN");
        DefaultShardManagerBuilder builder= DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("er zijn en staking op hele Nederland"));
        shardManager=builder.build();
       // shardManager.getGuildById(982617669884854322L).updateCommands();

       // shardManager.addEventListener(new EventListener());
        //shardManager.addEventListener(new MessageListener());

        CommandManager manager=new CommandManager();
        manager.add(new Play());
        manager.add(new Stop());
        manager.add(new Skip());
        shardManager.addEventListener(manager);






    }
    public static void main(String[] args) {
        try {
            OVchipkaart bot= new OVchipkaart();



        }
        catch (LoginException e){
            System.out.println("Error provided bot token is invalid");
        }


    }
}
