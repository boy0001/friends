package me.tkb7.friends;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.tkb7.friends.CommandHandler;

public class Main extends JavaPlugin {
    
    /*
     * 
     
          uuid friend list

        shows player names in game instead of uuids
        and possibly show player names besides uuids in friends.yml
        
        shows players that are online in green
        
        shows players that are offline in red and besides player name last time they were online
        
        allows you to click on friends list to deny/accept friend requests &
        enable/disable pvp with friends (disabled by default)
     
     * 
     */
    
    
    public static Plugin THIS = null;
    @Override
    public void onEnable() {
        this.THIS = this;
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return CommandHandler.onCommand(sender, cmd, label, args);
    }
    
    public static void log(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        if (console == null) {
            System.out.println(ChatColor.stripColor(message));
        }
        console.sendMessage(message);
    }
}