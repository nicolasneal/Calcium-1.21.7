package net.nicolas.calcium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.nicolas.calcium.block.ModBlocks;

public class CalciumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.putBlock(ModBlocks.ENDER_GROWTH, BlockRenderLayer.CUTOUT);

    }
}
