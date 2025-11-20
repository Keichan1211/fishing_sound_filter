package yourmod.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.util.math.Vec3d;
import yourmod.util.SoundBlocker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onPlaySound", at = @At("HEAD"), cancellable = true)
    private void fishingSoundFilter(PlaySoundS2CPacket packet, CallbackInfo ci) {

        String soundId = packet.getSound().value().id().toString();

        if (!SoundBlocker.isFishingSound(soundId)) {
            return;
        }

        Vec3d soundPos = new Vec3d(packet.getX(), packet.getY(), packet.getZ());

        if (SoundBlocker.shouldMuteFishingSound(soundPos)) {
            ci.cancel();
        }
    }
}
