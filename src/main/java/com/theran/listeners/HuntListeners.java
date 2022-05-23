package com.theran.listeners;

import com.theran.HuntMain;
import com.theran.utils.GameManager;
import com.theran.utils.HuntScoreboard;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HuntListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent){
        Player player = joinEvent.getPlayer(); //anashe

        player.setScoreboard(HuntScoreboard.newScoreboard());

        if(HuntMain.getStarted()){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.GRAY + "Game has already started, you're spectating!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        HuntMain.getPlayingPlayers().remove(event.getPlayer().getUniqueId());
        System.out.println("Removing " + event.getPlayer().getName() + " from list...");
    }

    @EventHandler
    public void onPlayerHurt(EntityDamageByEntityEvent event){
        if(!HuntMain.getStarted()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(HuntMain.getStarted()){
            Player killed = event.getEntity();
            System.out.println(killed.getName());
            Player hunter = killed.getKiller();

            if(hunter != null && HuntMain.hunterTarget.get(hunter.getUniqueId()) == killed.getUniqueId()){
                Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + hunter.getName() + " has killed their target!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT, 2.0f, 1.0f);
                });

                HuntMain.getPlayingPlayers().remove(killed.getUniqueId());

                //todo
                Location location = killed.getLocation();
                killed.setGameMode(GameMode.SPECTATOR);
                killed.teleport(location);
            }

            if(HuntMain.getPlayingPlayers().size() == 1)
                GameManager.endGame();
        }
    }
}
