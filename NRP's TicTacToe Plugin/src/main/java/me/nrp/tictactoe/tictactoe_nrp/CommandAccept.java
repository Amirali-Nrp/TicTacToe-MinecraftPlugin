package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.nrp.tictactoe.tictactoe_nrp.CommandChallenge.player1;
import static me.nrp.tictactoe.tictactoe_nrp.CommandChallenge.player2;

public class CommandAccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Game.game == null && player.equals(player2)) {
                    player.teleport(player1.getLocation().add(-3, 0, 0));
                    Game.game = new Game(player1, player2);
                    try {
                        Game.setSavename();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Game.game.gameBoard();
            }
            else if (Game.game != null && player.equals(Game.game.getPlayer2())) {
                player.sendMessage("you are already in a game!");
            }
            else
                player.sendMessage("You have not been invited to a game pal :(");
        }

        return false;
    }
}
