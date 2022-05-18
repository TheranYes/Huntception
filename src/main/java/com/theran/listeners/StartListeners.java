package com.theran.listeners;

import com.theran.HuntMain;
import com.theran.utils.HuntScoreboard;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StartListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent){
        Player player = joinEvent.getPlayer(); //anashe

        player.setScoreboard(HuntScoreboard.newScoreboard());

        if(HuntMain.started){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.GRAY + "Game has already started, you're spectating!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        HuntMain.playingPlayers.remove(event.getPlayer());
        System.out.println("Removing " + event.getPlayer().getName() + " from list...");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(HuntMain.started){
            Player killed = event.getEntity();
            System.out.println(killed.getName());
            Player hunter = (Player) killed.getKiller();

            if(hunter != null && HuntMain.hunterTarget.get(hunter) == killed){
                Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(
                        ChatColor.RED + hunter.getName() + " has killed their target!"));

                HuntMain.playingPlayers.remove(killed);
                killed.setGameMode(GameMode.SPECTATOR);
            }
        }
    }
}
