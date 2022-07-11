package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player2 = (Player) sender;
            if (CommandChallenge.player1 != null) {
                CommandChallenge.player1.sendMessage(player2.getDisplayName() + " denied your game invite :(");
                CommandChallenge.player1 = null;
                CommandChallenge.player2 = null;
            }
            else {
                player2.sendMessage("You have not been invited to a game!");
            }
        }
        return false;
    }
}
