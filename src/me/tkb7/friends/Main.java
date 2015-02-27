package me.tkb7.friends;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.tkb7.friends.CommandHandler;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return CommandHandler.onCommand(sender, cmd, label, args);
    }
}
