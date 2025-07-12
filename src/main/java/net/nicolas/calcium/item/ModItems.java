package net.nicolas.calcium.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final String MOD_ID = "calcium";

    public static final Item HIDE = register("hide", Item::new, new Item.Settings().maxCount(64));
    public static final Item FUR = register("fur", Item::new, new Item.Settings().maxCount(64));

    private static <T extends Item> T register(String name, Function<Item.Settings, T> constructor, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
        T item = constructor.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemgroup) -> {
            itemgroup.add(HIDE);
            itemgroup.add(FUR);
        });
    }
}