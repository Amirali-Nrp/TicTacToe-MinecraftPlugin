package me.nrp.tictactoe.tictactoe_nrp;

import org.bukkit.plugin.java.JavaPlugin;

public final class TicTacToe_NRP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("challenge").setExecutor(new CommandChallenge());
        getServer().getPluginCommand("accept").setExecutor(new CommandAccept());
        getServer().getPluginCommand("deny").setExecutor(new CommandDeny());
        getServer().getPluginCommand("put").setExecutor(new CommandPut());
        getServer().getPluginCommand("save").setExecutor(new CommandSave());
        getServer().getPluginCommand("load").setExecutor(new CommandLoad());
        getServer().getPluginCommand("tictactoehelp").setExecutor(new CommandHelp());
        getServer().getPluginManager().registerEvents(new ListenerPut(), this);
        getServer().getPluginManager().registerEvents(new Unbreakable(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
