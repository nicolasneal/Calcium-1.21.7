package net.nicolas.calcium.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockItem.class)
public abstract class CampfirePlacementMixin {

    @WrapOperation(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private static boolean changeBlockState(World instance, BlockPos pos, BlockState state, int flags, Operation<Boolean> original) {
        if (state.getBlock() instanceof CampfireBlock) {
            return instance.setBlockState(pos, state.with(CampfireBlock.LIT, false));
        }
        return original.call(instance,pos,state,flags);
    }

}