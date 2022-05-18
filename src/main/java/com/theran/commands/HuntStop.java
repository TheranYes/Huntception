package com.theran.commands;

import com.theran.HuntMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HuntStop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        HuntMain.started = false;
        sender.sendMessage(ChatColor.GRAY + "Game stopped.");
        return true;
    }
}
