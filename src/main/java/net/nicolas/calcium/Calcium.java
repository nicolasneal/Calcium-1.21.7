package net.nicolas.calcium;

import net.fabricmc.api.ModInitializer;
import net.nicolas.calcium.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calcium implements ModInitializer {

	public static final String MOD_ID = "calcium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		LOGGER.info("Calcium initialized.");
	}

}