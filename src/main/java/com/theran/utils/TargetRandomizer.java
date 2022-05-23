package com.theran.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.*;

public class TargetRandomizer {
    public static HashMap<UUID, UUID> randomizeTargets(ArrayList<UUID> playerList){

        HashMap<UUID, UUID> hunterTarget = new HashMap<>();

        Collections.shuffle(playerList);

        for(int i = 0; i < playerList.size(); i++){
            UUID hunter = playerList.get(i);
            UUID target;

            if(i == playerList.size() - 1)
                target = playerList.get(0);
            else
                target = playerList.get(i + 1);

            System.out.println("Hunter: " + hunter + ", Target: " + target);
            hunterTarget.put(hunter, target);
        }
        return hunterTarget;
    }
}
