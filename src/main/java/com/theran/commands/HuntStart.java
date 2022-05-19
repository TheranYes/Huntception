package com.theran.commands;

import com.theran.HuntMain;
import com.theran.utils.GameManager;
import com.theran.utils.TargetRandomizer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class HuntStart implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(Bukkit.getOnlinePlayers().size() <= 1 && !HuntMain.debugging){
            sender.sendMessage(ChatColor.RED + "Unable to start, the game requires 2 or more players.");
            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "Starting...");
        HuntMain.started = true;
        HuntMain.playingPlayers.clear();
        HuntMain.playingPlayers.addAll(Bukkit.getOnlinePlayers());

        GameManager.startRound();

        return true;
    }
}
