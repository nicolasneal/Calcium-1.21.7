package net.nicolas.calcium.event;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.nicolas.calcium.block.ModBlocks;

import java.util.Map;
import java.util.Optional;

public class Cracking {

    private static final Map<Block, Block> CRACKED_BLOCKS = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS)
            .put(ModBlocks.ANDESITE_BRICKS, ModBlocks.CRACKED_ANDESITE_BRICKS)
            .put(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS)
            .put(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .put(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES)
            .put(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS)
            .put(Blocks.CUT_SANDSTONE, ModBlocks.CRACKED_SANDSTONE_BRICKS)
            .put(Blocks.CUT_RED_SANDSTONE, ModBlocks.CRACKED_RED_SANDSTONE_BRICKS)
            .build();

    public static void registerEvents() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, hitResult) -> {
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            ItemStack itemStack = playerEntity.getStackInHand(hand);

            if (itemStack.isIn(ItemTags.PICKAXES)) {
                Optional<BlockState> crackedState = getCrackedState(blockState);
                if (crackedState.isPresent()) {
                    if (!world.isClient()) {
                        world.setBlockState(blockPos, crackedState.get(), Block.NOTIFY_ALL);
                        itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(hand));
                        ((ServerWorld) world).spawnParticles(
                                new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                                blockPos.getX() + 0.5,
                                blockPos.getY() + 0.5,
                                blockPos.getZ() + 0.5,
                                10,
                                0.25,
                                0.25,
                                0.25,
                                0.1
                        );
                    }
                    world.playSound(null, blockPos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    private static Optional<BlockState> getCrackedState(BlockState originalState) {
        return Optional.ofNullable(CRACKED_BLOCKS.get(originalState.getBlock()))
                .map(Block::getDefaultState);
    }

}