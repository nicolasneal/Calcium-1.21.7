package net.nicolas.calcium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.nicolas.calcium.block.ModBlocks;
import net.nicolas.calcium.screen.EnchantingScreen;

public class CalciumClient implements ClientModInitializer {

    @Override public void onInitializeClient() {

        HandledScreens.register(Calcium.ENCHANTING_SCREEN_HANDLER, EnchantingScreen::new);

    }
}
