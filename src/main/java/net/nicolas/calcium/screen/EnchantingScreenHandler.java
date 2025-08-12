package net.nicolas.calcium.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.nicolas.calcium.Calcium;
import net.nicolas.calcium.item.ModItems;
import net.nicolas.calcium.variables.SlotConfig;

public class EnchantingScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public EnchantingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(36));
    }

    public EnchantingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {

        super(Calcium.ENCHANTING_SCREEN_HANDLER, syncId);
        checkSize(inventory, 1);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        // Player Inventory Slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new CustomSlot.Builder(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18).build());
            }
        }

        // Hotbar Slots
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new CustomSlot.Builder(playerInventory, i, 8 + i * 18, 142).build());
        }

        // Tablet Slot
        this.addSlot(new CustomSlot.Builder(inventory, 0, 62, 44)
                .itemMode(SlotConfig.ItemMode.FIXED)
                .stackMode(SlotConfig.StackMode.SINGLE)
                .fixedItem(ModItems.ENCHANTING_TABLET)
                .tooltip(Text.translatable("tooltip.calcium.enchanting_table_tablet"))
                .build());

        // Ingredient Slots
        int[][] slotPositions = {
                {26, 53}, {26, 35}, {26, 17}, {44, 17}, {62, 17}, {80, 17}, {98, 17}, {98, 35}, {98, 53}
        };
        for (int i = 1; i <= 9; i++) {
            this.addSlot(new CustomSlot.Builder(inventory, i, slotPositions[i - 1][0], slotPositions[i - 1][1])
                    .itemMode(SlotConfig.ItemMode.ALL)
                    .stackMode(SlotConfig.StackMode.SINGLE)
                    .build());
        }

        // Fuel Slots
        int[][] fuelPositions = {
                {134, 53}, {134, 35}, {134, 17}
        };
        for (int i = 10; i <= 12; i++) {
            this.addSlot(new CustomSlot.Builder(inventory, i, fuelPositions[i - 10][0], fuelPositions[i - 10][1])
                    .itemMode(SlotConfig.ItemMode.FIXED)
                    .stackMode(SlotConfig.StackMode.STACK)
                    .fixedItem(Items.LAPIS_LAZULI)
                    .tooltip(Text.translatable("tooltip.calcium.enchanting_table_fuel"))
                    .build());
        }
    }

    @Override public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override public boolean canUse(PlayerEntity player) {
        return true;
    }

}
