package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class CommandLoad implements CommandExecutor {

    public static boolean isSure = false;

    private String path;

    private void load(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            for (int i = 0; i < 10; i++) {
                if (i < 9) {
                    Game.cell[i] = reader.readLine();
                }
                if (i == 9) {
                    Game.setI(Integer.parseInt(reader.readLine()));
                }
            }
            reader.close();
            Game.game.getPlayer2().teleport(Game.game.getPlayer1().getLocation().add(-3, 0, 0));
            Game.game.getPlayer1().sendMessage("Loaded!");
            Game.game.getPlayer2().sendMessage("Loaded!");
            Game.game.gameBoard();
        }
        catch (Exception ignored){}
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player playerr1 = (Player) sender;
            Player playerr2 = null;

            if (args[0].equals("yes") && !isSure && Game.game != null && (sender.equals(Game.game.getPlayer1()) || sender.equals(Game.game.getPlayer2()))) {
                isSure = true;
                load();
            }

            else if (args[0].equals("no") && !isSure && Game.game != null && (sender.equals(Game.game.getPlayer1()) || sender.equals(Game.game.getPlayer2()))) {
                Game.game.getPlayer1().sendMessage("Rejected!");
                Game.game.getPlayer2().sendMessage("Rejected!");
                Game.game = null;
                return false;
            }

            else {

                Game.setSavename(args[0]);

                try {
                    path = "C:\\Users\\amira\\OneDrive\\Desktop\\TicTocToeSaves\\" + args[0];

                    String p1 = String.valueOf(Files.readAllLines(Paths.get(path)).get(10));
                    String p2 = String.valueOf(Files.readAllLines(Paths.get(path)).get(11));

                    if (Game.game == null && (playerr1.getDisplayName().equals(p1) || playerr1.getDisplayName().equals(p2))) {
                        Game.setDone(Boolean.parseBoolean(Files.readAllLines(Paths.get(path)).get(12)));

                        Player player1 = null;
                        Player player2 = null;

                        if (Game.isDone()) {
                            playerr1.sendMessage("This game is finished!");
                            Game.game = null;
                            return true;
                        } else {
                            int c = 0;

                            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                                if (p.getDisplayName().equals(p1)) {
                                    player1 = p;
                                    c++;
                                }
                                if (p.getDisplayName().equals(p2)) {
                                    player2 = p;
                                    c++;
                                }
                            }

                            if (c < 2) {
                                playerr1.sendMessage("Your opponent is offline!");
                                Game.game = null;
                                return true;
                            }

                            assert player1 != null;
                            if (player1.equals(sender)) {
                                playerr2 = player2;
                            }
                            else {
                                assert player2 != null;
                                if (player2.equals(sender)) {
                                    playerr2 = player1;
                                }
                            }

                            Game.game = new Game(player1, player2);

                            assert playerr2 != null;
                            playerr2.sendMessage(playerr1.getDisplayName() + " wants to continue a TicTacToe game with you? Do you accept? [ /load yes or /load no ]");

                        }
                    } else if (playerr1.getDisplayName().equals(p1) || playerr1.getDisplayName().equals(p2)) {
                        playerr1.sendMessage("You are already in a game!");
                    } else {
                        playerr1.sendMessage("You are not in a game!");
                    }
                } catch (FileNotFoundException | NoSuchFileException e) {
                    playerr1.sendMessage("There is no game with this name!");
                }
                catch (IOException e) {
                     e.printStackTrace();
                 }
            }
        }
        return false;
    }
}
