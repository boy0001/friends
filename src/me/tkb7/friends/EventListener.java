package me.tkb7.friends;

import java.util.Collection;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventListener implements Listener {
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        FriendUtil.unregister(event.getPlayer().getUniqueId());
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        FriendUtil.unregister(event.getPlayer().getUniqueId());
    }
    
    @EventHandler(ignoreCancelled=true)
    public void arrow(EntityDamageByEntityEvent event) {
        
    }
    
    @EventHandler(ignoreCancelled=true)
    public void pvp(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity victim = event.getEntity();
        if (victim instanceof Player) {
            Player p1 = null;
            if (attacker instanceof Player) {
                p1 = (Player) attacker;
            }
            else {
                if (attacker instanceof Projectile) {
                    //Arrow, Egg, EnderPearl, Fireball, Fish, FishHook, LargeFireball, SmallFireball, Snowball, ThrownExpBottle, ThrownPotion, WitherSkull
                    if (attacker instanceof Arrow || attacker instanceof LargeFireball || attacker instanceof Fireball || attacker instanceof SmallFireball) {
                        LivingEntity shooter = ((Projectile) attacker).getShooter();
                        if (shooter == null || !(shooter instanceof Player)) {
                            return;
                        }
                        p1 = (Player) shooter;
                    }
                    else if (attacker instanceof ThrownPotion) {
                        ThrownPotion potion = (ThrownPotion) attacker;
                        Collection<PotionEffect> effects = potion.getEffects();
                        for (PotionEffect effect : effects) {
                            PotionEffectType type = effect.getType();
                            if (type == PotionEffectType.BLINDNESS || type == PotionEffectType.CONFUSION || type == PotionEffectType.HARM  || type == PotionEffectType.POISON  || type == PotionEffectType.SLOW || type == PotionEffectType.SLOW_DIGGING || type == PotionEffectType.WEAKNESS || type == PotionEffectType.WITHER) {
                                LivingEntity shooter = ((Projectile) attacker).getShooter();
                                if (shooter == null || !(shooter instanceof Player)) {
                                    return;
                                }
                                p1 = (Player) shooter;
                                break;
                            }
                        }
                        if (p1 == null) {
                            return;
                        }
                    }
                    else {
                        return;
                    }
                }
            }
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
