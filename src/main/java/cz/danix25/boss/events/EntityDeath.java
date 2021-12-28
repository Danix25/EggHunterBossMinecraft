package cz.danix25.boss.events;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {


    @EventHandler
    public void onEntityDeath (EntityDeathEvent e) {
        e.getDrops().clear();

        LivingEntity entity = e.getEntity();

        if(entity instanceof Skeleton) {
            entity.getLocation().getWorld().dropItem(entity.getLocation(), new ItemStack(Material.PLAYER_HEAD));

        }
    }
}
