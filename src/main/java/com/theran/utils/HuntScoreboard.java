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

    static String timeText = ChatColor.GREEN + "Time: 0:00";
    static String statusText = "Status: " + ChatColor.RED + "Waiting...";

    public static Scoreboard newScoreboard(){

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score line = objective.getScore(ChatColor.RED + "-=-=-=-=-=-=-=-=-=-=");
        status = objective.getScore(statusText);
        Score space1 = objective.getScore("");
        time = objective.getScore(timeText);
        Score line2 = objective.getScore(ChatColor.RED + "=-=-=-=-=-=-=-=-=-=-");

        line.setScore(5);
        status.setScore(4);
        space1.setScore(3);
        time.setScore(2);
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
        time.setScore(2);
        timeText = s;
    }

    public static void setStatus(boolean started){
        String s = "Status: ";
        if(started)
            s += ChatColor.GREEN + "Started";
        else
            s += ChatColor.RED + "Waiting...";

        if(!Objects.equals(statusText, s))
            objective.getScoreboard().resetScores(statusText);

        status = objective.getScore(s);
        status.setScore(4);
        statusText = s;
    }
}
