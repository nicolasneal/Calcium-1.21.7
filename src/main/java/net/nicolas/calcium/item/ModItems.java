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

    // INGREDIENTS
    public static final Item HIDE = register("hide", Item::new, new Item.Settings().maxCount(64));
    public static final Item FUR = register("fur", Item::new, new Item.Settings().maxCount(64));
    public static final Item PIXIE_DUST = register("pixie_dust", Item::new, new Item.Settings().maxCount(64));

    // FOOD AND DRINK
    public static final Item BEAR = register("bear", Item::new, new Item.Settings().maxCount(64).food(ModFoods.BEAR));
    public static final Item COOKED_BEAR = register("cooked_bear", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_BEAR));
    public static final Item SQUID = register("squid", Item::new, new Item.Settings().maxCount(64).food(ModFoods.SQUID));
    public static final Item CALAMARI = register("calamari", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CALAMARI));
    public static final Item FROG = register("frog", Item::new, new Item.Settings().maxCount(64).food(ModFoods.FROG));
    public static final Item COOKED_FROG = register("cooked_frog", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_FROG));
    public static final Item CHEVAL = register("cheval", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CHEVAL));
    public static final Item COOKED_CHEVAL = register("cooked_cheval", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CHEVAL));
    public static final Item CHEVON = register("chevon", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CHEVON));
    public static final Item COOKED_CHEVON = register("cooked_chevon", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CHEVON));
    public static final Item CAMEL = register("camel", Item::new, new Item.Settings().maxCount(64).food(ModFoods.CAMEL));
    public static final Item COOKED_CAMEL = register("cooked_camel", Item::new, new Item.Settings().maxCount(64).food(ModFoods.COOKED_CAMEL));

    private static <T extends Item> T register(String name, Function<Item.Settings, T> constructor, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
        T item = constructor.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemgroup) -> {
            itemgroup.add(HIDE);
            itemgroup.add(FUR);
            itemgroup.add(PIXIE_DUST);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(itemgroup -> {
            itemgroup.add(BEAR);
            itemgroup.add(COOKED_BEAR);
            itemgroup.add(SQUID);
            itemgroup.add(CALAMARI);
            itemgroup.add(FROG);
            itemgroup.add(COOKED_FROG);
            itemgroup.add(CHEVAL);
            itemgroup.add(COOKED_CHEVAL);
            itemgroup.add(CHEVON);
            itemgroup.add(COOKED_CHEVON);
            itemgroup.add(CAMEL);
            itemgroup.add(COOKED_CAMEL);
        });

    }
}