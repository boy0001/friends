package me.tkb7.friends;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        FriendUtil.unregister(event.getPlayer().getUniqueId());
    }
    
    @EventHandler(ignoreCancelled=true)
    public void onQuit(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity victim = event.getEntity();
        if (attacker instanceof Player && victim instanceof Player) {
            Player p1 = (Player) attacker;
            Player p2 = (Player) victim;
            
            if (!FriendUtil.getFriends(p1).contains(p2.getUniqueId())) { // not friends
                return;
            }
            Friendship link = new Friendship(p1.getUniqueId(), p2.getUniqueId());
            if (FriendUtil.pvp.contains(link)) {
                return;
            }
            // PVP not allowed because friends
            event.setCancelled(true);
        }
        
    }
    
}
