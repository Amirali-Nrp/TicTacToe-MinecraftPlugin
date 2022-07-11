package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("""
                            -> use '/challenge player1 player2' to start a new game.
                            -> use '/load gameName' to load a game.
                            -> use '/save' to save your game.
                            -> use '/put <a number between 1 - 9>' or right click to place your mark on game-board.
                            """);
        }
        return false;
    }
}
