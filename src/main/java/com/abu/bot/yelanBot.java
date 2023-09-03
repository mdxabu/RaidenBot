package com.abu.bot;

import com.abu.bot.commands.commandManager;
import com.abu.bot.events.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;



public class yelanBot {

    ShardManager shardmanager;
    public yelanBot() {
        String token = getenvToken();

        DefaultShardManagerBuilder bot = DefaultShardManagerBuilder.createDefault(token);

        bot.setActivity(Activity.playing("Genshin Impact")).setStatus(OnlineStatus.ONLINE);

        shardmanager = bot.build();

        bot.enableIntents(GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_PRESENCES);

        shardmanager.addEventListener(new EventListener() , new commandManager());
    }

    private static String getenvToken() {
        Dotenv config = Dotenv.configure().load();

        return config.get("TOKEN");
    }

}
