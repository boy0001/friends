package me.tkb7.friends;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TaskManager {
    private static int TASK = -1;;
    
    public static void startTask() {
        if (TASK != -1) {
            return;
        }
        Plugin plugin = Main.THIS;
        TASK = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                FriendUtil.saveFriends();
            }
        }, 24000L, 24000L);
    }
    
    public static void cancelTask() {
        if (TASK != -1) {
            Bukkit.getScheduler().cancelTask(TASK);
        }
    }
    
}
