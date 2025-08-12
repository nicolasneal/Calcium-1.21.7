package net.nicolas.calcium.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record EnchantingRecipeInput(ItemStack[] inputs) implements RecipeInput {

    public EnchantingRecipeInput {
        if (inputs == null || inputs.length != 9) {
            throw new IllegalArgumentException("inputs must be an array of length 9");
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= 9) return ItemStack.EMPTY;
        return inputs[slot];
    }

    @Override
    public int size() {
        return 9;
    }

}
