package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChallenge implements CommandExecutor {

    public static Player player1 = null;
    public static Player player2 = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            player1 = (Player) sender;
            if (args.length == 0) {
                player1.sendMessage("Give me a name dude :(");
                return true;
            }
            if (player1.getDisplayName().equals(args[0])) {
                player1.sendMessage("Cant play alone buddy :(");
                return true;
            }

            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.getDisplayName().equals(args[0])) {
                    player1.sendMessage("Player found!");
                    p.sendMessage(player1.getDisplayName() + " is inviting you to play TicTacToe together. Wanna have some fun? :)");
                    player2 = p;
                    return true;
                }
            }
            player1.sendMessage("Player not found! :(");
            return true;
        }

        return false;
    }
}
