package com.theran.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.*;

public class TargetRandomizer {
    public static HashMap<Player, Player> randomizeTargets(ArrayList<Player> playerList){

        HashMap<Player, Player> hunterTarget = new HashMap<>();

        Collections.shuffle(playerList);

        for(int i = 0; i < playerList.size(); i++){
            Player hunter = playerList.get(i);
            Player target;

            if(i == playerList.size() - 1)
                target = playerList.get(0);
            else
                target = playerList.get(i + 1);

            if(playerList.size() > 2)
                hunter.sendMessage(ChatColor.AQUA + "Your target is " + target.getName() + ". Eliminate them!");

            System.out.println("Hunter: " + hunter + ", Target: " + target);
            hunterTarget.put(hunter, target);
        }

        if(playerList.size() == 2)
            Bukkit.getOnlinePlayers().forEach(player ->
                    player.sendMessage(ChatColor.RED + "Two hunters remain! " + playerList.get(0).getName() + "'s and " +
                            playerList.get(1).getName() + "'s duel begins!"));
        return hunterTarget;
    }
}
