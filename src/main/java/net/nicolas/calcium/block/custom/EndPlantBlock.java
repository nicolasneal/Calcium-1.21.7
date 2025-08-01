package net.nicolas.calcium.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.block.Blocks;

public class EndPlantBlock extends ShortPlantBlock {

    public EndPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.END_STONE);
    }
}