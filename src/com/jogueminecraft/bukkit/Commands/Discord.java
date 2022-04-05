package com.jogueminecraft.bukkit.Commands;

import com.jogueminecraft.bukkit.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Discord implements CommandExecutor {
    Main plugin;

    public Discord(Main instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando pode ser usado somente no jogo!");
            return true;
        }
        Player player = (Player) sender;
        if ((cmd.getName().equalsIgnoreCase("discord") || cmd.getName().equalsIgnoreCase("dc")) && args.length == 0) {
            player.sendMessage(" ");
            player.sendMessage(ChatColor.BOLD + "DISCORD");
            player.sendMessage(ChatColor.DARK_GRAY + "Clique para entrar em nossa comunidade:");
            player.sendMessage(ChatColor.GRAY + (plugin.getConfig().getString("discord")));
            player.sendMessage(" ");
        }
        return false;
    }
}