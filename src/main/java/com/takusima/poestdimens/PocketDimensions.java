package com.takusima.poestdimens;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

public class PocketDimensions implements ModInitializer {
    public static final String MOD_ID = "poestdimens";

    @Override
    public void onInitialize() {
        // Регистрация конфига
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

        // Регистрация команд
        CommandRegistry.register();

        // Регистрация тикера защиты границ
        PocketTicker.register();

        // Регистрация магических команд в чате
        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, typeKey) -> {
            String msg = message.signedContent().plain().trim();
            if (msg.equalsIgnoreCase("Prunus")) {
                PocketCommands.handlePrunus(sender);
                return false;
            }
            if (msg.equalsIgnoreCase("Malus")) {
                PocketCommands.handleMalus(sender);
                return false;
            }
            return true;
        });
    }
}