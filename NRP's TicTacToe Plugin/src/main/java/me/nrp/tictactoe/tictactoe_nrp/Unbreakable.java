package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Unbreakable implements Listener {

    @EventHandler
    public void unbreakable(PlayerInteractEvent event){
        if (event.getHand() == EquipmentSlot.HAND) {
            Location l = event.getPlayer().getTargetBlockExact(6).getLocation();
            for (Location loc : Game.locations) {
                if (loc.equals(l)) {
                    if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}