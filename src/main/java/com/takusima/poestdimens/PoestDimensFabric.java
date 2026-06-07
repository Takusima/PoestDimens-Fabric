package com.takusima.poestdimens;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PocketDimensions implements ModInitializer {
    public static final String MOD_ID = "poestdimens";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Pocket Dimensions mod for Fabric");
        
        // Register config
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        
        // Register commands
        PocketCommandRegistration.register();
        
        // Register tick events and chat events
        PocketCommands.register();
        
        LOGGER.info("Pocket Dimensions mod initialized successfully!");
    }
}