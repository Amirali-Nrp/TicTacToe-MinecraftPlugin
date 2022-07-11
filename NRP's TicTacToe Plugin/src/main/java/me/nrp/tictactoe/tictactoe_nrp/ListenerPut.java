package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ListenerPut implements Listener {
    @EventHandler
    public void put(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            Material m = e.getPlayer().getTargetBlock(null, 1000).getType();
            if (m == Material.WHITE_WOOL) {
                int pos = Game.getLocations().indexOf(e.getPlayer().getTargetBlockExact(1000).getLocation()) + 1;
                e.getPlayer().performCommand("put " + pos);
            }
        }
    }
}
