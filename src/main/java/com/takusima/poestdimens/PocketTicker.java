package com.takusima.poestdimens;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;

public class PocketTicker {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.level().dimension().equals(ModDimensions.POCKET_DIM_KEY)) {
                    double x = player.getX();
                    double z = player.getZ();

                    // Находим центр текущей платформы на сетке (шаг 2000 блоков по оси X)
                    long currentWorldId = Math.round(x / 2000.0);
                    double relativeX = x - (currentWorldId * 2000);

                    // Корректируем границы:
                    // Радиус платформы в менеджере равен 32 блокам (стены стоят на координатах centerX +- 32 и centerZ +- 32).
                    // Ставим проверку на > 34, чтобы триггер смерти срабатывал только за пределами невидимых стен-барьеров.
                    if (Math.abs(relativeX) > 34 || Math.abs(z) > 34 || player.getY() < -64) {
                        player.hurt(player.damageSources().source(DamageTypes.GENERIC), Float.MAX_VALUE);
                    }
                }
            }
        });
    }
}