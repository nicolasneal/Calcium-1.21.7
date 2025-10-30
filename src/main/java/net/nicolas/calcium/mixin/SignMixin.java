package net.nicolas.calcium.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class SignMixin {

    @Inject(
            method = "onSignEditorOpen",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelSignEditor(SignEditorOpenS2CPacket packet, CallbackInfo ci) {
        ci.cancel();
    }

}