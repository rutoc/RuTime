package com.jogueminecraft.bukkit.Commands;

import com.jogueminecraft.bukkit.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;

public class Light implements CommandExecutor {
    Main plugin;
    public Light(Main instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Esse comando pode ser usado somente no jogo!");
            return true;
        }
        Player player = (Player)sender;
        if ((cmd.getName().equalsIgnoreCase("light") || cmd.getName().equalsIgnoreCase("luz")) && args.length == 0) {
            if (hasPotionEffect(player, PotionEffectType.NIGHT_VISION, 50)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);

                player.sendTitle(ChatColor.RED + "DESLIGADO!", "Efeito removido com sucesso!");
                return true;
            }
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 50), true);
                player.sendTitle(ChatColor.LIGHT_PURPLE + "LIGADO!", "Efeito adicionado com sucesso!");
                return true;
        }
        return false;
    }

    public static boolean hasPotionEffect(Player player, PotionEffectType type, int amplifier) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(type) && effect.getAmplifier() == amplifier)
                return true;
        }
        return false;
    }

}