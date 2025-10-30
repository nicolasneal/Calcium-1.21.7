package net.nicolas.calcium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.nicolas.calcium.block.ModBlocks;
import net.nicolas.calcium.event.Cracking;
import net.nicolas.calcium.item.ModItems;
import net.nicolas.calcium.screen.EnchantingScreenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calcium implements ModInitializer {

	public static final String MOD_ID = "calcium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Registering Screens
	public static final ScreenHandlerType<EnchantingScreenHandler> ENCHANTING_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, Identifier.of("calcium", "enchanting_screen"),
					new ScreenHandlerType<>(EnchantingScreenHandler::new, FeatureSet.of(FeatureFlags.VANILLA)));

	@Override public void onInitialize() {

		// Custom Class Initialization
		ModItems.initialize();
		ModBlocks.initialize();
		Cracking.registerEvents();

		// Registering Items as Fuels
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.PIXIE_DUST, 2400);
			builder.add(ModItems.OAK_TIMBER, 300);
			builder.add(ModItems.OAK_PLANK, 300);
			builder.add(ModItems.WOODEN_ROD, 200);
		});

		// Overriding Item Stack Sizes
		DefaultItemComponentEvents.MODIFY.register(context -> {
			// Standard Signs
			context.modify(Items.OAK_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SPRUCE_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BIRCH_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.JUNGLE_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ACACIA_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.DARK_OAK_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MANGROVE_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CHERRY_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PALE_OAK_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BAMBOO_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CRIMSON_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.WARPED_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Hanging Signs
			context.modify(Items.OAK_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SPRUCE_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BIRCH_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.JUNGLE_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ACACIA_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.DARK_OAK_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MANGROVE_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CHERRY_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PALE_OAK_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BAMBOO_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CRIMSON_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.WARPED_HANGING_SIGN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Beds
			context.modify(Items.WHITE_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ORANGE_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MAGENTA_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIGHT_BLUE_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.YELLOW_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIME_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PINK_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GRAY_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIGHT_GRAY_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CYAN_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PURPLE_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BLUE_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BROWN_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GREEN_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.RED_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BLACK_BED, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Banners
			context.modify(Items.WHITE_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ORANGE_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MAGENTA_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIGHT_BLUE_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.YELLOW_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIME_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PINK_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GRAY_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LIGHT_GRAY_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CYAN_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PURPLE_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BLUE_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BROWN_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GREEN_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.RED_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BLACK_BANNER, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Music Discs
			context.modify(Items.MUSIC_DISC_13, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_CAT, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_BLOCKS, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_CHIRP, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_FAR, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_MALL, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_MELLOHI, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_STAL, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_STRAD, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_WARD, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_11, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_WAIT, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_PIGSTEP, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_5, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_RELIC, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_CREATOR, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_CREATOR_MUSIC_BOX, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_PRECIPICE, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_TEARS, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSIC_DISC_LAVA_CHICKEN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Banner Patterns
			context.modify(Items.FIELD_MASONED_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BORDURE_INDENTED_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.FLOWER_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CREEPER_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SKULL_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MOJANG_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GLOBE_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.PIGLIN_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.FLOW_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.GUSTER_BANNER_PATTERN, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Throwables
			context.modify(Items.ENDER_PEARL, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SNOWBALL, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BLUE_EGG, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.EGG, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BROWN_EGG, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			// Miscellaneous
			context.modify(Items.BUCKET, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.POTION, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SPLASH_POTION, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.LINGERING_POTION, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.HONEY_BOTTLE, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.CAKE, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.MUSHROOM_STEW, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.BEETROOT_SOUP, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.RABBIT_STEW, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.SUSPICIOUS_STEW, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ARMOR_STAND, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.WRITTEN_BOOK, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
			context.modify(Items.ENCHANTED_BOOK, builder -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
		});

	}

}