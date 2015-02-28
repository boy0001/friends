package me.tkb7.friends;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FriendUtil {
    private static HashMap<UUID, HashSet<UUID>> friendMap = new HashMap<>();
    
    public static File friendFile;
    public static YamlConfiguration friendYml;
    
    public static HashMap<Friendship, Long> pendingFriendships = new HashMap<>();
    
    public static HashSet<Friendship> pvp = new HashSet<>();
    
    public static int cooldown = 60;
    
    public static void unregister(UUID uuid) {
        for (Friendship link : pendingFriendships.keySet()) {
            if (link.user1.equals(uuid) || link.user2.equals(uuid)) {
                pendingFriendships.remove(link);
                return;
            }
        }
        for (Friendship link : pvp) {
            if (link.user1.equals(uuid) || link.user2.equals(uuid)) {
                pvp.remove(link);
                return;
            }
        }
    }
    
    public static UUID getUUID(String name) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(name);
        if (op.hasPlayedBefore()) {
            return op.getUniqueId();
        }
        return null;
    }
    
    public static String getName(UUID uuid) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
        if (op.hasPlayedBefore()) {
            return op.getName();
        }
        return null;
    }
    
    public static Set<UUID> getFriends(Player player) {
        return getFriends(player.getUniqueId());
    }
    
    public static Set<UUID> getFriends(UUID uuid) {
        HashSet<UUID> friends = friendMap.get(uuid);
        if (friends == null) {
            return new HashSet<UUID>();
        }
        return friends;
    }
    
    public static void saveFriends() {
        try {
            if (!friendFile.exists()) {
                friendFile.createNewFile();
            }
            for (Entry<UUID, HashSet<UUID>> entry : friendMap.entrySet()) {
                List<String> friends = new ArrayList<String>();
                for (UUID friend : entry.getValue()) {
                    friends.add(friend.toString());
                }
            }
        }
        catch (IOException e) {
            Main.log("FAILED TO LOAD FRIENDS FILE");
            return;
        }
    }
    
    public static void addFriend(UUID user, UUID friend) {
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
    
    public static void removeFriend(UUID user, UUID friend) {
        HashSet<UUID> friends = friendMap.get(user);
        if (friends != null) {
            friends.remove(friend);
        }
        friends = friendMap.get(friend);
        if (friends != null) {
            friends.remove(user);
        }
    }
    
    public static void loadFriends() {
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
