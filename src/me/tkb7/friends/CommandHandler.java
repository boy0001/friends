package me.tkb7.friends;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHandler {

    public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String command = label.toLowerCase();
        
        switch (command) {
            case "add": {
                // do add command
            }
            case "list": {
                // do list command
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
