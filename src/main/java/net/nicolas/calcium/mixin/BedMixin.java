package net.nicolas.calcium.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class BedMixin {

    @Shadow public ClientPlayerEntity player;

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void cancelChatScreen(net.minecraft.client.gui.screen.Screen screen, CallbackInfo ci) {
        if (this.player != null && this.player.isSleeping() && screen instanceof ChatScreen) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        ClientPlayerEntity player = client.player;

        if (player != null && player.isSleeping()) {
            long window = client.getWindow().getHandle();

            boolean sneakPressed = InputUtil.isKeyPressed(
                    window,
                    InputUtil.fromTranslationKey(client.options.sneakKey.getBoundKeyTranslationKey()).getCode()
            );
            boolean perspectivePressed = client.options.togglePerspectiveKey.isPressed();

            for (KeyBinding key : client.options.allKeys) {
                key.setPressed(false);
            }

            if (sneakPressed) {
                client.getNetworkHandler().sendPacket(
                        new ClientCommandC2SPacket(player, ClientCommandC2SPacket.Mode.STOP_SLEEPING)
                );
            }

            client.options.sneakKey.setPressed(sneakPressed);
            client.options.togglePerspectiveKey.setPressed(perspectivePressed);
            client.options.attackKey.setPressed(false);
            client.options.useKey.setPressed(false);

        }
    }

    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void onDoAttack(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player != null && client.player.isSleeping()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "doItemUse", at = @At("HEAD"), cancellable = true)
    private void onDoItemUse(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player != null && client.player.isSleeping()) {
            ci.cancel();
        }
    }

}
