package com.theran.commands;

import com.theran.HuntMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HuntDebug implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        HuntMain.setDebugging(!HuntMain.getDebugging());

        if (HuntMain.getDebugging()) {
            sender.sendMessage(ChatColor.AQUA + "Debugging enabled");
        } else {
            sender.sendMessage(ChatColor.DARK_AQUA + "Debugging off");
        }

        return true;
    }
}
