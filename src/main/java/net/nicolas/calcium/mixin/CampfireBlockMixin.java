package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {

    @Inject(method = "onUseWithItem", at = @At("HEAD"), cancellable = true)
    private void onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (state.get(CampfireBlock.LIT)) return;
        if (state.get(CampfireBlock.WATERLOGGED)) return;

        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.STICK)) {
            world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.4F + 0.8F);

            if (!world.isClient) {
                if (world.random.nextFloat() < 0.30F) {
                    world.setBlockState(pos, state.with(CampfireBlock.LIT, true));
                }
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
            }

            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
        }
    }

}
