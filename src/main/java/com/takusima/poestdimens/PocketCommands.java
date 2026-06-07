package com.takusima.poestdimens;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import java.util.*;

public class PocketCommands {
    public static void handlePrunus(ServerPlayer p) {
        if (!PocketWorldManager.get(p.serverLevel()).hasPocket(p.getUUID())) {
            if (p.getInventory().countItem(Items.ENDER_PEARL) < 1) return;
            p.getInventory().clearOrCountMatchingItems(s -> s.is(Items.ENDER_PEARL), 1, p.inventoryMenu.getCraftSlots());
        }
        executeTeleport(p, p.getUUID());
    }

    public static void sendRequest(ServerPlayer guest, ServerPlayer host) {
        host.sendSystemMessage(Component.literal("§e" + guest.getName().getString() + " просится в мир!"));
        guest.sendSystemMessage(Component.literal("§7Запрос отправлен."));
    }

    public static void executeTeleport(ServerPlayer p, UUID owner) {
        var level = p.getServer().getLevel(ModDimensions.POCKET_DIM_KEY);
        var pos = PocketWorldManager.get(level).getOrCreatePocket(owner, level);
        p.teleportTo(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, p.getYRot(), p.getXRot());
    }

    public static void handleMalus(ServerPlayer p) {
        var overworld = p.getServer().overworld();
        p.teleportTo(overworld, overworld.getSharedSpawnPos().getX(), 70, overworld.getSharedSpawnPos().getZ(), 0, 0);
    }
}