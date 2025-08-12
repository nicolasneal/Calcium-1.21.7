package net.nicolas.calcium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record EnchantingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<EnchantingRecipeInput> {

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(EnchantingRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(EnchantingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<EnchantingRecipeInput>> getSerializer() {
        return ModRecipes.ENCHANTING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<EnchantingRecipeInput>> getType() {
        return ModRecipes.ENCHANTING_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<EnchantingRecipe> {
        public static final MapCodec<EnchantingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(EnchantingRecipe::inputItem),
            ItemStack.CODEC.fieldOf("result").forGetter(EnchantingRecipe::output)
        ).apply(inst, EnchantingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, EnchantingRecipe> STREAM_CODEC =
            PacketCodec.tuple(
                Ingredient.PACKET_CODEC, EnchantingRecipe::inputItem,
                ItemStack.PACKET_CODEC, EnchantingRecipe::output,
                EnchantingRecipe::new);

        @Override
        public MapCodec<EnchantingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, EnchantingRecipe> packetCodec() {
            return STREAM_CODEC;
        }

    }

}