package net.nicolas.calcium.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CampfireBlock.class)
public abstract class CampfirePlacementMixin {

    @ModifyReturnValue(
            method = "getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;",
            at = @At("RETURN")
    )
    private BlockState calcium$unlitCampfirePlacementState(BlockState originalState) {
        return originalState.with(CampfireBlock.LIT, false);
    }
}