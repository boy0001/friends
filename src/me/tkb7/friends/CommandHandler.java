package me.tkb7.friends;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHandler {

    public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            // No sub command provided
            return false;
        }
        String command = args[0].toLowerCase();
        
        switch (command) {
            case "add": {
                // do add command
            }
            case "list": {
                // do list command
            }
            case "accept": {
                
            }
            case "remove": {
                // do remove command
            }
            default: {
                // unknown sub command entered by user
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
