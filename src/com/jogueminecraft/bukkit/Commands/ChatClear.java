package com.jogueminecraft.bukkit.Commands;

import com.jogueminecraft.bukkit.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class ChatClear implements CommandExecutor {
    Main plugin;

    public ChatClear(Main instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando pode ser usado somente no jogo!");
            return true;
        }

        Player player = (Player) sender;
        int numero = 100;

        if (player.hasPermission("chat.clear")) {
            if ((cmd.getName().equalsIgnoreCase("clearchat") || cmd.getName().equalsIgnoreCase("limparchat")) && args.length == 0) {
                for (int i = numero; i > 0; i--) {
                    Bukkit.broadcastMessage(" ");
                }
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " " + (plugin.getConfig().getString("language.chat.clearchat")));
                player.sendTitle(ChatColor.RED + (plugin.getConfig().getString("language.title.clearchat")), (plugin.getConfig().getString("language.subtitle.clearchat")));
            }
            return false;
        }
        player.sendTitle(ChatColor.RED + (plugin.getConfig().getString("language.title.permissions")), (plugin.getConfig().getString("language.subtitle.permissions")));
        return true;
    }
}