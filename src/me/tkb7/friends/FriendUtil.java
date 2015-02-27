package me.tkb7.friends;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FriendUtil {
    private HashMap<UUID, HashSet<UUID>> friendMap = new HashMap<>();
    
    public static File friendFile;
    public static YamlConfiguration friendYml;
    
    public UUID getUUID(String name) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(name);
        if (op.hasPlayedBefore()) {
            return op.getUniqueId();
        }
        return null;
    }
    
    public void addFriend(UUID user, UUID friend) {
        HashSet<UUID> friends = friendMap.get(user);
        if (friends == null) {
            friendMap.put(user, new HashSet<>(Arrays.asList(friend)));
        }
        else {
            friends.add(friend);
        }
        friends = friendMap.get(friend);
        if (friends == null) {
            friendMap.put(user, new HashSet<>(Arrays.asList(user)));
        }
        else {
            friends.add(user);
        }
    }
    
    public void removeFriend(UUID user, UUID friend) {
        HashSet<UUID> friends = friendMap.get(user);
        if (friends != null) {
            friends.remove(friend);
        }
        friends = friendMap.get(friend);
        if (friends != null) {
            friends.remove(user);
        }
    }
    
    public void loadFriends() {
        try {
            String folder = Main.THIS.getDataFolder() + "";
            friendFile = new File(folder + File.separator + "friends.yml");
            if (!friendFile.exists()) {
                friendFile.createNewFile();
            }
            friendYml = YamlConfiguration.loadConfiguration(friendFile);
            ConfigurationSection section = friendYml.getConfigurationSection("friends");
            if (section == null) {
                friendYml.createSection("friends");
                return;
            }
            Set<String> users = section.getKeys(false);
            for (String user : users) {
                UUID uuid = UUID.fromString(user);
                HashSet<UUID> friends = new HashSet<>();
                for (String friend : section.getStringList(user)) {
                    friends.add(UUID.fromString(friend));
                }
                if (friends.size() > 0) {
                    friendMap.put(uuid, friends);
                }
            }
        }
        catch (IOException e) {
            Main.log("FAILED TO LOAD FRIENDS FILE");
            return;
        }
    }
    
    
}
