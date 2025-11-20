package yourmod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class SoundBlocker {

    public static boolean shouldMuteFishingSound(Vec3d pos) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world == null || client.player == null) {
            return false;
        }

        for (var entity : client.world.getEntities()) {
            if (entity instanceof FishingBobberEntity bobber) {
                if (bobber.getPos().distanceTo(pos) < 2.0) {
                    if (bobber.getOwner() != client.player) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isFishingSound(String soundId) {
        return soundId.equals(SoundEvents.ENTITY_FISHING_BOBBER_SPLASH.getId().toString())
            || soundId.equals(SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE.getId().toString());
    }
}
