package me.tkb7.friends;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler {

	@SuppressWarnings("deprecation")
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
            		} else {/* Check if player is not on friends list if player is already on list */
					Player fp = Bukkit.getPlayer(friend);
					String pf = p.getName();
					p.sendMessage(ChatColor.GREEN + "Friend request sent to " + friend);
					fp.sendMessage(ChatColor.GOLD + pf + ChatColor.GRAY + " would like to be your friend.");
            		    /* Put json chat stuffs here to accept or decline friend request 
            		     * 
            		     * if decline    p.sendMessage(ChatColor.GOLD + friend + ChatColor.RED + " has declined to be your friend.");
            		     * 
            		     * if accept     p.sendMessage(ChatColor.GOLD + friend + ChatColor.GREEN + " has accepted to be your friend!");
            		     *               FriendUtil.addFriend(p.getUniqueId(), uuid);
            		     */ 
				}
			} else {
				p.sendMessage(ChatColor.RED + "Incorrect Syntax use /Friends Help for help");
			}
		}
		case "help": {
			if (args.length == 1) {
            		p.sendMessage(ChatColor.GOLD + "Friends Help");
            		p.sendMessage(ChatColor.YELLOW + "/Friend(s) " + ChatColor.GRAY + "Shows your friends list");
            		p.sendMessage(ChatColor.YELLOW + "/Friend(s) add (Friend Name) " + ChatColor.GRAY + "Allows you to add friends to your friends list");
			} else {
            		p.sendMessage(ChatColor.RED + "Incorrect Syntax use /Friends Help for help");
			}
		}
		default: {
                // list player's friends with "Toggle PvP" , "Remove" &  "Last time online" next to all of there names
		}
		}
		return false;
	}
}
