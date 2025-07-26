package net.nicolas.calcium.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nicolas.calcium.block.custom.EndPlantBlock;

import java.util.function.Function;

public class ModBlocks {

    public static final String MOD_ID = "calcium";

    // CUBE BLOCKS
    public static final Block POLISHED_STONE = register("polished_stone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block ANDESITE_BRICKS = register("andesite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_ANDESITE = register("chiseled_andesite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block DIORITE_BRICKS = register("diorite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_DIORITE = register("chiseled_diorite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block GRANITE_BRICKS = register("granite_bricks", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block CHISELED_GRANITE = register("chiseled_granite", Block::new, Block.Settings.create().sounds(BlockSoundGroup.STONE).mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F), true);
    public static final Block POLISHED_DRIPSTONE = register("polished_dripstone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.DRIPSTONE_BLOCK).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 1.0F), true);
    public static final Block CHISELED_DRIPSTONE = register("chiseled_dripstone", Block::new, Block.Settings.create().sounds(BlockSoundGroup.DRIPSTONE_BLOCK).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 1.0F), true);

    // NON-CUBE BLOCKS
    public static final Block WOODEN_PRESSURE_PLATE = register("wooden_pressure_plate", properties -> new PressurePlateBlock(BlockSetType.OAK, properties), AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).sounds(BlockSoundGroup.WOOD).strength(2.0F, 0.5F).burnable(), true);
    public static final Block STONE_PRESSURE_PLATE = register("stone_pressure_plate", properties -> new PressurePlateBlock(BlockSetType.STONE, properties), AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).sounds(BlockSoundGroup.WOOD).strength(2.0F, 0.5F).burnable(), true);
    // PLANT BLOCKS
    public static final Block ENDER_GROWTH = register("ender_growth", EndPlantBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS).offset(AbstractBlock.OffsetType.XYZ).burnable().pistonBehavior(PistonBehavior.DESTROY), true);

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((itemgroup) -> {
            itemgroup.add(ENDER_GROWTH);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((itemgroup) -> {
            itemgroup.add(POLISHED_STONE);
            itemgroup.add(ANDESITE_BRICKS);
            itemgroup.add(CHISELED_ANDESITE);
            itemgroup.add(DIORITE_BRICKS);
            itemgroup.add(CHISELED_DIORITE);
            itemgroup.add(GRANITE_BRICKS);
            itemgroup.add(CHISELED_GRANITE);
            itemgroup.add(POLISHED_DRIPSTONE);
            itemgroup.add(CHISELED_DRIPSTONE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register((itemgroup) -> {
            itemgroup.add(WOODEN_PRESSURE_PLATE);
            itemgroup.add(STONE_PRESSURE_PLATE);
        });

    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean registerItem) {

        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (registerItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
    }

}