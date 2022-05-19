package com.theran.listeners;

import com.theran.HuntMain;
import com.theran.utils.GameManager;
import com.theran.utils.HuntScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HuntListeners implements Listener {
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

    /*
    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent event){
        Player hurt = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(!(HuntMain.hunterTarget.get(damager) == hurt) && HuntMain.started){
            damager.sendMessage(ChatColor.RED + "You can only damage your target!");
            event.setCancelled(true);
        }
    } */

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(HuntMain.started){
            Player killed = event.getEntity();
            System.out.println(killed.getName());
            Player hunter = killed.getKiller();

            if(hunter != null && HuntMain.hunterTarget.get(hunter) == killed){
                Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(
                        ChatColor.RED + "" + ChatColor.BOLD + hunter.getName() + " has killed their target!"));

                HuntMain.playingPlayers.remove(killed);

                //todo
                Location location = killed.getLocation();
                killed.setGameMode(GameMode.SPECTATOR);
                killed.teleport(location);
            }

            if(HuntMain.playingPlayers.size() == 1)
                GameManager.endGame();
        }
    }
}
