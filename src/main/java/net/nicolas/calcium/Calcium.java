package net.nicolas.calcium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.FuelRegistry;
import net.nicolas.calcium.block.ModBlocks;
import net.nicolas.calcium.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calcium implements ModInitializer {

	public static final String MOD_ID = "calcium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.initialize();
		ModBlocks.initialize();
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.PIXIE_DUST, 2400);
		});

	}

}