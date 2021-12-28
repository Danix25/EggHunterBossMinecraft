package cz.danix25.boss;

import cz.danix25.boss.events.PlayerDestroyBlock;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Boss extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("se zapnul");
        getServer().getPluginManager().registerEvents(new PlayerDestroyBlock(this), this);


    }

    @Override
    public void onDisable() {
        getLogger().info("se vypnul");
    }
}
