package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPut implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            int pos;
            try {
                pos = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                player.sendMessage("please give me a number between 1 - 9.");
                return false;
            }
            try {
                if (player.equals(Game.game.getPlayer1())) {
                    if (Game.getI() % 2 != 0) {
                        if (Game.cell[pos].equals("X") || Game.cell[pos].equals("O")) {
                            player.sendMessage("Cant put here! :(");
                            return false;
                        }
                        Game.cell[pos] = "X";
                        Game.locations.get(pos).getBlock().setType(Material.RED_WOOL);
                        Game.checkWinner();
                        if (Game.isDone()) {
                            Game.game = null;
                            return true;
                        }
                        Game.setI(Game.getI() + 1);
                        return true;
                    } else {
                        player.sendMessage("It's not your turn!");
                    }
                } else if (player.equals(Game.game.getPlayer2())) {
                    if (Game.getI() % 2 == 0) {
                        if (Game.cell[pos].equals("X") || Game.cell[pos].equals("O")) {
                            player.sendMessage("Cant put here! :(");
                            return false;
                        }
                        Game.cell[pos] = "O";
                        Game.locations.get(pos).getBlock().setType(Material.BLUE_WOOL);
                        Game.checkWinner();
                        if (Game.isDone()) {
                            Game.game = null;
                            return true;
                        }
                        Game.setI(Game.getI() + 1);
                        return true;
                    } else {
                        player.sendMessage("It's not your turn!");
                    }
                }
            }
            catch (NullPointerException e) {
                player.sendMessage("You are not in a game!");
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                player.sendMessage("please give me a number between 1 - 9.");
                return false;
            }
        }
        return false;
    }
}
