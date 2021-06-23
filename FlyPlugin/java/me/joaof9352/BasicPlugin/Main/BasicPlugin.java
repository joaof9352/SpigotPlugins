package me.joaof9352.BasicPlugin.Main;

import me.joaof9352.BasicPlugin.commands.Broadcaster;
import me.joaof9352.BasicPlugin.commands.Flyer;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //getCommand("bc").setExecutor(new Broadcaster());
        getCommand("fly").setExecutor(new Flyer(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
