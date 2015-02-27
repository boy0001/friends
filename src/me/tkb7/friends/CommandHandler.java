package me.tkb7.friends;

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
