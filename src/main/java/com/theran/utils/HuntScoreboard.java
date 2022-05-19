package com.theran.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class HuntScoreboard {

    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard scoreboard = manager.getNewScoreboard();
    static Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.DARK_RED + "" + ChatColor.BOLD + "<-- HUNTCEPTION -->");

    static Score status;
    static Score time;
    static Score target;

    static String timeText = ChatColor.GREEN + "Time: 0:00";

    public static Scoreboard newScoreboard(){

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score line = objective.getScore(ChatColor.RED + "-=-=-=-=-=-=-=-=-=-=");
        status = objective.getScore("Status: " + ChatColor.RED + "Waiting...");
        Score space1 = objective.getScore("");
        time = objective.getScore(timeText);
        Score line2 = objective.getScore(ChatColor.RED + "=-=-=-=-=-=-=-=-=-=-");

        target = objective.getScore(ChatColor.RED + "Target: None");
        Score space2 = objective.getScore(" ");

        line.setScore(7);
        status.setScore(6);
        space1.setScore(5);
        time.setScore(4);
        space2.setScore(3);
        target.setScore(2);
        line2.setScore(1);

        return scoreboard;
    }

    public static void setTime(int totalSeconds){
        int minutes = totalSeconds/60;
        int seconds = totalSeconds % 60;
        String s = ChatColor.GREEN + "Time: ";

        if(seconds < 10)
            s += minutes + ":0" + seconds;
        else
            s += minutes + ":" + seconds;

        //System.out.println("Time: " + s);

        if(!Objects.equals(timeText, s)){
            objective.getScoreboard().resetScores(timeText);
        }

        time = objective.getScore(s);
        time.setScore(4);
        timeText = s;
    }
}
