package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.io.*;
import java.util.ArrayList;

public class Game {

    public static Game game;

    private static Player player1;

    private static Player player2;

    public static ArrayList<Location> locations = new ArrayList<>();

    public static ArrayList<Location> locs = new ArrayList<>();

    private static boolean done = false;

    private static int i = 1;

    static String[] cell = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static int gID = 1;

    private static String savename;

    public Game(Player player1, Player player2) {
        Game.player1 = player1;
        Game.player2 = player2;
        cell = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        i = 1;
        done = false;
        game = this;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        Game.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public static boolean isDone() {
        return done;
    }

    public static void setDone(boolean done) {
        Game.done = done;
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Game.i = i;
    }

    public static String[] getCell() {
        return cell;
    }

    public static void setCell(String[] cell) {
        Game.cell = cell;
    }

    public static ArrayList<Location> getLocations() {
        return locations;
    }

    public static void setLocations(ArrayList<Location> locations) {
        Game.locations = locations;
    }

    public static String getSavename() {
        return savename;
    }

    public static void setSavename(String savename) {
        Game.savename = savename;
    }

    public void gameBoard() {

        locations = new ArrayList<>();

        locs = new ArrayList<>();

        World world = player1.getWorld();

        int x = player1.getLocation().getBlockX();
        int y = player1.getLocation().getBlockY() + 4;
        int z = player1.getLocation().getBlockZ() + 9;

        for (int t = 0; t < 4; t++) {
            player1.getLocation().add(0, t, 11 - t).getBlock().setType(Material.PRISMARINE_BRICK_STAIRS);
            locs.add(new Location(world, x, y - 4 + t, z + (11 - t) - 9));
        }

        for (int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                player1.getLocation().add( n - 2, 3, 4 + m).getBlock().setType(Material.GLASS);
                locs.add(new Location(world, x + (n - 2), y - 1, z + m - 5));
                if ((m == 0 || m==4) && (n == 0 || n==4)) {
                    if (n == 0 && m == 4) {
                        player1.getLocation().add(n - 2, 4, 4 + m).getBlock().setType(Material.SOUL_CAMPFIRE);
                    }
                    else if (m == 4) {
                        player1.getLocation().add(n - 2, 4, 4 + m).getBlock().setType(Material.WARPED_SIGN);
                    }
                    else {
                        player1.getLocation().add(n - 2, 4, 4 + m).getBlock().setType(Material.SOUL_TORCH);
                    }
                    locs.add(new Location(world, x + (n - 2), y, z + m - 5));
                }
            }
        }

        Sign sign = (Sign) player1.getWorld().getBlockAt(player1.getLocation().add(2 ,4 , 8)).getState();
        sign.setLine(0, "Welcome");
        sign.update();
        sign.setLine(1, "to");
        sign.update();
        sign.setLine(2, "TicTacToe");
        sign.update();

        for (int i = 2; i > -1; i--) {
            for (int j = -1; j < 2; j++) {
                locations.add(new Location(world, x + j, y + i, z - 5));
            }
        }
        for (Location location : locations) {
            location.getBlock().setType(Material.WHITE_WOOL);
        }
        for (int k = 0; k < 9; k++) {
            if (cell[k].equals("X")) {
                locations.get(k).getBlock().setType(Material.RED_WOOL);
            }
            if (cell[k].equals("O")) {
                locations.get(k).getBlock().setType(Material.BLUE_WOOL);
            }
        }
    }

    public static void checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> cell[0] + cell[1] + cell[2];
                case 1 -> cell[3] + cell[4] + cell[5];
                case 2 -> cell[6] + cell[7] + cell[8];
                case 3 -> cell[0] + cell[3] + cell[6];
                case 4 -> cell[1] + cell[4] + cell[7];
                case 5 -> cell[2] + cell[5] + cell[8];
                case 6 -> cell[0] + cell[4] + cell[8];
                case 7 -> cell[2] + cell[4] + cell[6];
                default -> null;
            };

            if (line.equals("XXX")) {
                player1.sendMessage("You win!");
                player2.sendMessage("You lose!");
                done = true;
                fireWorks(player1, "red");
                Save();
            }

            else if (line.equals("OOO")) {
                player2.sendMessage("You win!");
                player1.sendMessage("You lose!");
                done = true;
                fireWorks(player2, "blue");
                Save();
            }
        }
        if (i == 9){
            player1.sendMessage("Draw!");
            player2.sendMessage("Draw!");
            fireWorks(player1, "red");
            fireWorks(player2, "blue");
            done = true;
            Save();
        }
    }

    public static void fireWorks(Player winner, String color) {
        Firework firework = winner.getWorld().spawn(winner.getLocation(), Firework.class);
        FireworkMeta data = firework.getFireworkMeta();
        switch (color) {
            case "red" -> data.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.fromRGB(218,165,32)).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
            case "blue" -> data.addEffect(FireworkEffect.builder().withColor(Color.BLUE).withColor(Color.fromRGB(218,165,32)).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
        }
        data.setPower(1);
        firework.setFireworkMeta(data);
    }

    public static void setSavename() throws IOException {

        try {

            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\amira\\OneDrive\\Desktop\\TicTocToeSaves\\gID.txt"));

            gID = Integer.parseInt(reader.readLine());

            gID++;

            savename = player1.getDisplayName() + "_" + player2.getDisplayName() + "_" + gID + ".txt";

            FileWriter writer = new FileWriter("C:\\Users\\amira\\OneDrive\\Desktop\\TicTocToeSaves\\gID.txt", false);

            writer.write(String.valueOf(gID));
            writer.close();

        }
        catch (FileNotFoundException e){
            FileWriter writer = new FileWriter("C:\\Users\\amira\\OneDrive\\Desktop\\TicTocToeSaves\\gID.txt");

            writer.write(String.valueOf(1));
            writer.close();

            savename = player1.getDisplayName() + "_" + player2.getDisplayName() + "_" + gID + ".txt";
        }
    }

    public static void Save(){

        try{

            String savepath = "C:\\Users\\amira\\OneDrive\\Desktop\\TicTocToeSaves\\" + savename;

            FileWriter writer = new FileWriter(savepath);

            for(int j = 0; j < 9; j++){
                writer.write(cell[j] + System.lineSeparator());
            }

            String cnt = Integer.toString(i);

            writer.write(cnt + System.lineSeparator());
            writer.write(player1.getDisplayName() + System.lineSeparator());
            writer.write(player2.getDisplayName() + System.lineSeparator());
            writer.write(String.valueOf(done));

            writer.close();

            Game.game.getPlayer1().sendMessage(Game.getSavename() + " Saved successfully!");
            Game.game.getPlayer2().sendMessage(Game.getSavename() + " Saved successfully!");

            for (Location location : locations) {
                location.getBlock().setType(Material.AIR);
            }

            for (Location location : locs) {
                location.getBlock().setType(Material.AIR);
            }

        }
        catch (IOException ignored) {
        }
        Game.game = null;
        CommandChallenge.player1 = null;
        CommandChallenge.player2 = null;
        CommandLoad.isSure = false;
    }
}
