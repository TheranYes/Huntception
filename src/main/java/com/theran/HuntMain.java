package com.theran;

import com.theran.commands.HuntDebug;
import com.theran.commands.HuntStart;
import com.theran.commands.HuntStop;
import com.theran.listeners.HuntListeners;
import com.theran.utils.HuntScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class HuntMain extends JavaPlugin {

    private static HuntMain instance;

    public static HashMap<UUID, UUID> hunterTarget = new HashMap<>();
    private static boolean debugging = false;
    private static boolean started = false;
    private static ArrayList<UUID> playingPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //reloading while online
        Bukkit.getOnlinePlayers().forEach(player -> playingPlayers.add(player.getUniqueId()));

        Scoreboard scoreboard = HuntScoreboard.newScoreboard();
        Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(scoreboard));

        getServer().getPluginManager().registerEvents(new HuntListeners(), this);
        getCommand("huntstart").setExecutor(new HuntStart());
        getCommand("huntdebug").setExecutor(new HuntDebug());
        getCommand("huntstop").setExecutor(new HuntStop());

        System.out.println("Huntception ON!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Turning off Huntception...");
    }

    public static HuntMain getInstance(){
        return instance;
    }

    public static boolean getStarted() {
        return started;
    }

    public static void setStarted(boolean started){
        HuntMain.started = started;
    }

    public static ArrayList<UUID> getPlayingPlayers() {
        return playingPlayers;
    }

    public static boolean getDebugging(){
        return debugging;
    }

    public static void setDebugging(boolean debugging){
        HuntMain.debugging = debugging;
    }
}
