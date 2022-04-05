package com.jogueminecraft.bukkit;

import com.jogueminecraft.bukkit.Commands.Light;
import com.jogueminecraft.bukkit.Commands.Discord;
import org.bukkit.World;
import com.jogueminecraft.bukkit.Commands.ChatClear;
import com.jogueminecraft.bukkit.Events.WeatherListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        getLogger().info("Esse plugin foi habilitado!");
        if ((getConfig().getBoolean("day-night-cicle")) == false) {
            getLogger().info("Modo: Apenas dia!");
            new BukkitRunnable(){
                @Override
                public void run() {
                    for (World world : Bukkit.getWorlds()){
                        world.setTime(0);
                    }
                }
            }.runTaskTimer(this,0,1);
        } else getLogger().info("Modo: Ciclo dia e noite!");
        getCommands();
        getInstance();
        getRegister();
        getConfigs();
    }

    public void getRegister() {
        getRegisterEvent((Listener)new WeatherListener(this), (Plugin)this);
    }

    public void getRegisterEvent(Listener arg0, Plugin plugin) {
        getServer().getPluginManager().registerEvents(arg0, plugin);
    }

    public void getCommands() {
        getCommand("light", (CommandExecutor)new Light(this));
        getCommand("clearchat", (CommandExecutor)new ChatClear(this));
        getCommand("discord", (CommandExecutor)new Discord (this));
    }

    public void getCommand(String Command, CommandExecutor Executor) {
        Bukkit.getServer().getPluginCommand(Command).setExecutor(Executor);
    }

    public void getConfigs() {
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("day-night-cicle", true);
        getConfig().addDefault("no-rain", true);
        getConfig().addDefault("discord", "https://discord.gg/GgZs7xkGJR");
        getConfig().addDefault("language.title.permissions", "ERRO!");
        getConfig().addDefault("language.subtitle.permissions", "Você não tem permissões!");
        getConfig().addDefault("language.title.clearchat", "CHAT LIMPO!");
        getConfig().addDefault("language.subtitle.clearchat", "Você limpou o bate-papo com sucesso!");
        getConfig().addDefault("language.chat.clearchat", "limpou o bate-papo com sucesso!");
        getConfig().addDefault("language.title.light-on", "LIGADO!");
        getConfig().addDefault("language.subtitle.light-on", "Efeito ativado com sucesso!");
        getConfig().addDefault("language.title.light-off", "DESLIGADO!");
        getConfig().addDefault("language.subtitle.light-off", "Efeito desativado com sucesso!");

        saveConfig();
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }

    @EventHandler
    public void PlayerKick(PlayerKickEvent e) {
        e.setLeaveMessage(null);
    }
}
