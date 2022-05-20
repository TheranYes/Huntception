package com.theran.utils;

import com.theran.HuntMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameManager {

    static CountdownTimer timer;

    public static void startRound(){
        HuntMain.hunterTarget = TargetRandomizer.randomizeTargets(HuntMain.playingPlayers);

        timer = new CountdownTimer(HuntMain.getInstance(), 120,
                () -> {},
                () -> {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.AQUA + "Changing targets..."));
                    startRound();
                },
                (t) -> HuntScoreboard.setTime(t.getSecondsLeft())
        );

        timer.scheduleTimer();
    }

    public static void endGame(){
        Bukkit.getScheduler().cancelTask(timer.getAssignedTaskId());
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage("xd termino"));
        HuntMain.setStarted(false);
        HuntScoreboard.setStatus(false);
        /*
        timer = new CountdownTimer(HuntMain.getInstance(), 10,
                () -> {Bukkit}
        );
         */
    }
}
