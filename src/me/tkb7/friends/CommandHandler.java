package me.tkb7.friends;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler {

    public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            // No sub command provided
            return false;
        }
        String command = args[0].toLowerCase();
        Player p = (Player) sender;
        
        switch (command) {
            case "add": {
            	if (args.length == 2) {
            		String friend = args[1];
            		UUID uuid = FriendUtil.getUUID(friend);
            		if (uuid == null) {
            		    p.sendMessage(ChatColor.RED + "Player not found");
            		}
            		else {
            		    FriendUtil.addFriend(p.getUniqueId(), uuid);
            		    p.sendMessage(ChatColor.GREEN + "YAY");
            		}
            	} else {
            		p.sendMessage(ChatColor.RED + "Incorrect Syntax use /Friends Help for help");
            	}
            }
            case "help": {
            	if (args.length == 2) {
            		
            	} else {
            		p.sendMessage(ChatColor.RED + "Incorrect Syntax use /Friends Help for help");
            	}
            }
            default: {
                // list player's friends
            }
        }
        return false;
    }
    /*
     * 
     *  
     *  
     *  /friend <add|list|remove>
     *  
     *  
     *  
     *  
     *  
     */
}
