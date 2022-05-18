package com.theran.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class HuntScoreboard {
    public static Scoreboard newScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.DARK_RED + "" + ChatColor.BOLD + "<-- HUNTCEPTION -->");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score time = objective.getScore(ChatColor.GREEN + "Time: 0:00");
        Score space1 = objective.getScore("");
        Score target = objective.getScore(ChatColor.RED + "Target: None");
        Score space2 = objective.getScore("");

        space1.setScore(4);
        time.setScore(3);
        space2.setScore(2);
        target.setScore(1);

        return scoreboard;
    }
}
