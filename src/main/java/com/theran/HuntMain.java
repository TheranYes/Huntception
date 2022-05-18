package com.theran;

import com.theran.commands.HuntDebug;
import com.theran.commands.HuntStart;
import com.theran.commands.HuntStop;
import com.theran.listeners.StartListeners;
import com.theran.utils.HuntScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;

public final class HuntMain extends JavaPlugin {

    public static HashMap<Player, Player> hunterTarget =new HashMap<>();
    public static boolean debugging = false;
    public static boolean started = false;
    public static ArrayList<Player> playingPlayers = new ArrayList<Player>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Huntception ON!");

        //reloading while online
        playingPlayers.addAll(Bukkit.getOnlinePlayers());

        Scoreboard scoreboard = HuntScoreboard.newScoreboard();
        Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(scoreboard));

        getServer().getPluginManager().registerEvents(new StartListeners(), this);
        getCommand("huntstart").setExecutor(new HuntStart());
        getCommand("huntdebug").setExecutor(new HuntDebug());
        getCommand("huntstop").setExecutor(new HuntStop());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Turning off Huntception...");
    }
}
