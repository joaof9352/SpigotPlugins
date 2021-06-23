package me.joaof9352.BasicPlugin.commands;

import me.joaof9352.BasicPlugin.Main.BasicPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Flyer implements CommandExecutor {

    private BasicPlugin bP;
    List<Player> flyingPlayers;

    public Flyer(BasicPlugin bP){
        flyingPlayers = new ArrayList<>();
        this.bP = bP;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /fly

        if(!(sender instanceof Player) && args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Only players can do that!");
            return false;
        }
        if(args.length == 0){
            Player p = (Player) sender;
            if(p.hasPermission("flyplugin.fly"))
                flyMethod(p);
            else
                p.sendMessage(ChatColor.translateAlternateColorCodes('%',bP.getConfig().getString("prefix") + bP.getConfig().getString("fly.no-perm")));
        }else{
            Player p = (Player) Bukkit.getPlayer(args[0]);
            if(sender.hasPermission("flyplugin.flyOthers")) {
                if (p != null)
                    flyMethod(p);
                else
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('%', bP.getConfig().getString("prefix") + bP.getConfig().getString("fly.player-not-found")));
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('%',bP.getConfig().getString("prefix") + bP.getConfig().getString("fly.no-perm")));
            }
        }
        return false;
    }

    private void flyMethod(Player p){
        if(flyingPlayers.contains(p)){
            p.setAllowFlight(false);
            flyingPlayers.remove(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('%',bP.getConfig().getString("prefix") + bP.getConfig().getString("fly.flight-off")));
        }else{
            p.setAllowFlight(true);
            flyingPlayers.add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('%',bP.getConfig().getString("prefix") + bP.getConfig().getString("fly.flight-on")));
        }
    }
}
