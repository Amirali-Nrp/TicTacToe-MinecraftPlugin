package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSave implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(Game.game != null && (p.getDisplayName().equals(Game.game.getPlayer1().getDisplayName()) || p.getDisplayName().equals(Game.game.getPlayer2().getDisplayName()))) {
                Game.Save();
            }
            else
                p.sendMessage("You are not in a game!");
        }
        return false;
    }
}
