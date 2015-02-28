package me.tkb7.friends;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.bobacadodl.JSONChatLib.JSONChatClickEventType;
import com.bobacadodl.JSONChatLib.JSONChatColor;
import com.bobacadodl.JSONChatLib.JSONChatExtra;
import com.bobacadodl.JSONChatLib.JSONChatFormat;
import com.bobacadodl.JSONChatLib.JSONChatHoverEventType;
import com.bobacadodl.JSONChatLib.JSONChatMessage;

import me.tkb7.friends.CommandHandler;

public class Main extends JavaPlugin implements Listener {
    
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
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.THIS = this;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        JSONChatMessage message = new JSONChatMessage("Hey, ", JSONChatColor.AQUA, null);
        JSONChatExtra extra = new JSONChatExtra("<Click This>", JSONChatColor.BLUE, Arrays.asList(JSONChatFormat.BOLD));
        extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "Example Hover Text");
        extra.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/example-command");
        message.addExtra(extra);
        message.sendToPlayer(event.getPlayer());
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