package me.frkovo.mmhelp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener {
    public static String mmid = "KA";
    public static int s = 0;
    public static JavaPlugin instance;
    public static FileConfiguration cfg = null;
    public static Material material = Material.NETHERITE_AXE;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        cfg = getConfig();
        Bukkit.getPluginCommand("mmhelp").setExecutor(new cmd());
        Bukkit.getPluginManager().registerEvents(this,this);
    }
    @EventHandler
    public void onCKA(PlayerInteractEvent e){
        if(e.getAction() != Action.LEFT_CLICK_BLOCK)return;
        e.setCancelled(true);
        if(e.getPlayer().getInventory().getItemInMainHand().getType() != material)return;
        if(!e.getPlayer().hasPermission("mmhelp.use"))return;
        Bukkit.dispatchCommand(e.getPlayer(),cfg.getString("format").replace("%id%",mmid).replace("%c%",String.valueOf(s)));
        e.getPlayer().sendMessage("§aSUCCESS - "+mmid+" - #"+s);
        s++;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
