package cz.danix25.boss.events;

import cz.danix25.boss.Boss;
import org.bukkit.*;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.bukkit.util.Vector;


import java.util.concurrent.ThreadLocalRandom;

public class PlayerDestroyBlock implements Listener {

    private Boss plugin;
    public PlayerDestroyBlock(Boss main){
        this.plugin = main;
    }

    @EventHandler
    public void PlayerBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(e.getBlock().getType()== Material.PLAYER_HEAD){
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            p.getLocation().getWorld().setTime(1000);
            Skeleton skeleton = e.getBlock().getWorld().spawn(e.getBlock().getLocation().add(0.5,0,0.5),Skeleton.class);
            skeleton.setCustomName("§e§lEggHunter");
            skeleton.setCustomNameVisible(true);
            skeleton.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
            skeleton.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            skeleton.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            skeleton.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            skeleton.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            skeleton.setHealth(20);
            skeleton.setMaximumNoDamageTicks(40);
            skeleton.setFallDistance(0);
            skeleton.setPersistent(true);
            skeleton.attack(p);
            skeleton.isGlowing();
            skeleton.setCanPickupItems(false);
            skeleton.setFireTicks(1);
            skeleton.setAbsorptionAmount(20);
            skeleton.setGliding(true);
            skeleton.setTarget(p);
            skeleton.isPersistent();
            skeleton.setMetadata("EggHunter",new FixedMetadataValue(plugin,"egghunter"));
            p.spawnParticle(Particle.LAVA, p.getLocation().add(0,2,0), 100);
            p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,25,10);
            p.setVelocity(p.getLocation().getDirection().multiply(-1));
        }
    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Skeleton && e.getDamager() instanceof Player){
            Player player = (Player)e.getDamager();
            player.spawnParticle(Particle.SWEEP_ATTACK, player.getLocation().add(0,2,0), 5);
            if(e.getEntity().hasMetadata("EggHunter")){
                int random = ThreadLocalRandom.current().nextInt(10);
                if(random < 8){
                    e.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL,10,10);
                    player.sendMessage("§c§lTvuj utok byl vyblokovan");
                    player.spawnParticle(Particle.BARRIER, player.getLocation().add(0,0,0), 1, 1 );
                }
            }
        }
        if(e.getDamager() instanceof Skeleton && e.getEntity() instanceof Player){
            if(e.getDamager().hasMetadata("EggHunter")){
                int random = ThreadLocalRandom.current().nextInt(10);
                if(random<5){
                    e.setCancelled(true);
                    Player player = (Player)e.getEntity();
                    player.setVelocity(new Vector(0,2,0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60,2));
                    player.sendMessage("§4§lByl jsi vyhozen do vzduchu");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMITE);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMITE);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMITE);
                    player.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation().add(0,2,0), 25);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP,15,10);
                }

                if(random<4){
                    e.setCancelled(true);
                    Player player = (Player)e.getEntity();
                    player.setVelocity(new Vector(0,0,0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10,2));
                    player.sendMessage("§4§lByl jsi zpomalen");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
                    player.spawnParticle(Particle.SNEEZE, player.getLocation().add(0,2,0), 25);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY,15,10);
                }
                if(random<3){
                    e.setCancelled(true);
                    Player player = (Player)e.getEntity();
                    player.setVelocity(new Vector(0,2,0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,2,2));
                    player.sendMessage("§4§lByl jsi nakazen");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    player.spawnParticle(Particle.SPELL_MOB_AMBIENT, player.getLocation().add(0,2,0), 50);
                    player.playSound(player.getLocation(), Sound.ENTITY_VINDICATOR_DEATH,15,10);
                }
                if(random<2){
                    e.setCancelled(true);
                    Player player = (Player)e.getEntity();
                    player.setVelocity(new Vector(0,2,0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,10,2));
                    player.sendMessage("§4§lVznasis se");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
                    player.spawnParticle(Particle.CLOUD, player.getLocation().add(0,2,0), 50);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_SMALL_FALL,15,10);

                }
                if(random<1){
                    e.setCancelled(true);
                    Player player = (Player)e.getEntity();
                    player.setVelocity(new Vector(0,2,0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,10,2));
                    player.sendMessage("§4§lMas halucinace");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    player.spawnParticle(Particle.SPELL_MOB_AMBIENT, player.getLocation().add(0,2,0), 75);
                    player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_HIT_GROUND,15,10);

                }
            }
        }




    }
}
