package com.theran.utils;

import com.theran.HuntMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

public class GameManager {

    public static void startPreRound(){
        HuntMain huntMain = HuntMain.getInstance();
        int preRoundSeconds = huntMain.getConfig().getInt("pre-round-seconds");

        if(preRoundSeconds <= 0){
            startRound();
            return;
        }

        HuntScoreboard.setStatus(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "In Pre-round");

        CountdownTimer preRoundTimer;
        preRoundTimer = new CountdownTimer(HuntMain.getInstance(), huntMain.getConfig().getInt("pre-round-seconds"),
                () -> Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You have " + preRoundSeconds + " seconds to get resources!");
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2.0f, 1.0f);
                }),
                () -> startRound(),
                (t) -> {
                    HuntScoreboard.setTime(t.getSecondsLeft());

                    float ratio = (float)  t.getSecondsLeft() / t.getTotalSeconds();
                    if(ratio == 0.5 || ratio == 0.75 || ratio == 0.25 || t.getSecondsLeft() <= 5) {
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "" + t.getSecondsLeft() + " second(s) remaining until game starts!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 1.0f);
                        });
                    }
                }
        );

        preRoundTimer.scheduleTimer();
    }

    static CountdownTimer timer;

    public static void startRound(){

        HuntMain huntMain = HuntMain.getInstance();
        if(huntMain.getConfig().getInt("seconds-per-round") <= 0){
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.RED + "There's a mistake inside the config, are you sure the time inputted is correct?"));
            endGame();
        }

        HuntMain.hunterTarget = TargetRandomizer.randomizeTargets(HuntMain.getPlayingPlayers());

        ArrayList<UUID> players = HuntMain.getPlayingPlayers();

        if(players.size() > 2)
            players.forEach(player -> Bukkit.getPlayer(player).sendMessage(ChatColor.AQUA + "Your target is " + Bukkit.getPlayer(HuntMain.hunterTarget.get(player)).getName() + ". Eliminate them!"));

        if(players.size() == 2)
            Bukkit.getOnlinePlayers().forEach(player ->
                    player.sendMessage(ChatColor.RED + "Two hunters remain! " + Bukkit.getPlayer(players.get(0)).getName() + "'s and " +
                            Bukkit.getPlayer(players.get(1)).getName() + "'s duel begins!"));

        HuntScoreboard.setStatus(ChatColor.GREEN + "" + ChatColor.BOLD + "Started");

        timer = new CountdownTimer(HuntMain.getInstance(), huntMain.getConfig().getInt("seconds-per-round"),
                () -> {},
                () -> {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.AQUA + "Changing targets..."));
                    startRound();
                },
                (t) -> {
                    HuntScoreboard.setTime(t.getSecondsLeft());
                    float ratio = (float)  t.getSecondsLeft() / t.getTotalSeconds();
                    if(ratio == 0.5 || ratio == 0.75 || ratio == 0.25 || t.getSecondsLeft() <= 5) {
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            player.sendMessage(ChatColor.AQUA + "" + t.getSecondsLeft() + " second(s) remaining until target change!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 1.0f);
                        });
                    }
                }
        );

        timer.scheduleTimer();
    }

    public static void endGame(){
        Bukkit.getScheduler().cancelTask(timer.getAssignedTaskId());

        HuntMain.setStarted(false);

        if(!HuntMain.getDebugging()){
            Player winner = Bukkit.getPlayer(HuntMain.getPlayingPlayers().get(0));

            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(ChatColor.GOLD + "The winner is " + winner.getName() + "! Good game!");
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 2.0f, 1.0f);

                if(player != winner){
                    player.setGameMode(GameMode.ADVENTURE);
                    player.teleport(winner.getLocation());
                }
            });
        }
        else
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("xd termino"));

        HuntScoreboard.setStatus(ChatColor.RED + "" + ChatColor.BOLD + "Not Started");
        /*
        timer = new CountdownTimer(HuntMain.getInstance(), 10,
                () -> {Bukkit}
        );
         */
    }
}
