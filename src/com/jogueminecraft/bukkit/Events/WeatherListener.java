package com.jogueminecraft.bukkit.Events;

import com.jogueminecraft.bukkit.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {
    Main plugin;

    public WeatherListener(Main instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onWeatherStorm(WeatherChangeEvent event) {
        if ((plugin.getConfig().getBoolean("no-rain")) == true){
        if (event.toWeatherState()) {
            event.getWorld().setWeatherDuration(0);
            event.setCancelled(true);
        }
        }
    }
}
