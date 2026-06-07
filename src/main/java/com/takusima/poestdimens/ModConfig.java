package com.takusima.poestdimens;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = PocketDimensions.MOD_ID)
public class ModConfig implements ConfigData {
    
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int prunusXpCost = 2;
    
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int prunusHungerCost = 4;
    
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public String particleColor = "#FF0000";
}