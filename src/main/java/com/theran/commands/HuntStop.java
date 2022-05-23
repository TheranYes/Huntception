package com.theran.commands;

import com.theran.HuntMain;
import com.theran.utils.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HuntStop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameManager.endGame();
        sender.sendMessage(ChatColor.GRAY + "Game stopped forcefully.");
        return true;
    }
}
