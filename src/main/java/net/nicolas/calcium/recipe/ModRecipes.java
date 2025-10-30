package net.nicolas.calcium.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<EnchantingRecipe> ENCHANTING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of("calcium", "enchanting"),
            new EnchantingRecipe.Serializer()
    );

    public static final RecipeType<EnchantingRecipe> ENCHANTING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of("calcium", "enchanting"),
            new RecipeType<EnchantingRecipe>() {
                @Override
                public String toString() {
                    return "enchanting";
                }
            }
    );

}