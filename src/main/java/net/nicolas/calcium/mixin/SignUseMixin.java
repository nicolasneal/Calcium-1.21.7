package net.nicolas.calcium.mixin;

import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class SignUseMixin {

    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void onRightClick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.crosshairTarget instanceof BlockHitResult hit) {
            World world = client.world;
            if (world == null) return;

            BlockPos pos = hit.getBlockPos();

            if (world.getBlockState(pos).getBlock() instanceof SignBlock) {
                if (world.getBlockEntity(pos) instanceof SignBlockEntity sign) {
                    client.execute(() -> {
                        client.setScreen(new SignEditScreen(sign,true,true));
                    });
                }
            }

        }
    }

}
