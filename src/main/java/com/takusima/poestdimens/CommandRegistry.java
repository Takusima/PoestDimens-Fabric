package com.example.pocketdimensions;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CommandRegistry {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("Remna")
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                            .executes(context -> {
                                ServerPlayer player = context.getSource().getPlayerOrException();
                                String name = StringArgumentType.getString(context, "name");
                                PocketWorldManager.get(player.serverLevel()).setWorldName(player.getUUID(), name);
                                return 1;
                            })));

            dispatcher.register(Commands.literal("Prequest")
                    .then(Commands.argument("target", EntityArgument.player())
                            .executes(context -> {
                                ServerPlayer guest = context.getSource().getPlayerOrException();
                                ServerPlayer host = EntityArgument.getPlayer(context, "target");
                                // Логика обработки запроса
                                return 1;
                            })));
        });
    }
}