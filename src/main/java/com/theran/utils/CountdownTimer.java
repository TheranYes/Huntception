package com.theran.utils;

import com.theran.HuntMain;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class CountdownTimer implements Runnable {

    private final HuntMain plugin;
    private Integer assignedTaskId;

    private final int seconds;
    private int secondsLeft;

    //actions
    private final Consumer<CountdownTimer> everySecond;
    private final Runnable beforeTimer;
    private final Runnable afterTimer;

    public CountdownTimer(HuntMain plugin, int seconds, Runnable beforeTimer,
                          Runnable afterTimer, Consumer<CountdownTimer> everySecond){
        this.plugin = plugin;

        this.seconds = seconds;
        this.secondsLeft = seconds;

        this.beforeTimer = beforeTimer;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }



    @Override
    public void run() {
        if(secondsLeft == seconds)
            beforeTimer.run();

        if(secondsLeft < 1) {
            afterTimer.run();

            if(assignedTaskId != null)
                Bukkit.getScheduler().cancelTask(assignedTaskId);

            return;
        }

        everySecond.accept(this);
        secondsLeft--;
    }

    public void scheduleTimer(){
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }

    public int getTotalSeconds(){
        return seconds;
    }

    public int getSecondsLeft(){
        return secondsLeft;
    }

    public Integer getAssignedTaskId(){
        return assignedTaskId;
    }
}
