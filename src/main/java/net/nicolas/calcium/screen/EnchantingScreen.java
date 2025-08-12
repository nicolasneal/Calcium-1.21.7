package net.nicolas.calcium.screen;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnchantingScreen extends HandledScreen<EnchantingScreenHandler> {

    private static final Identifier TEXTURE = Identifier.of("calcium", "textures/gui/container/enchanting_table.png");

    public EnchantingScreen(EnchantingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        Slot slot = this.getSlotUnderMouse();
        if (slot instanceof CustomSlot customSlot) {
            Text tooltip = customSlot.getTooltip();
            if (tooltip != null) {
                context.drawTooltip(this.textRenderer, tooltip, mouseX, mouseY);
            }
        }
    }

    private Slot getSlotUnderMouse() {

        double mouseX = this.client.mouse.getX() * this.client.getWindow().getScaledWidth() / this.client.getWindow().getWidth();
        double mouseY = this.client.mouse.getY() * this.client.getWindow().getScaledHeight() / this.client.getWindow().getHeight();
        int guiLeft = this.x;
        int guiTop = this.y;
        int relativeX = (int) mouseX - guiLeft;
        int relativeY = (int) mouseY - guiTop;

        for (Slot slot : this.handler.slots) {
            int centerX = slot.x + 8;
            int centerY = slot.y + 8;
            if (relativeX >= centerX - 18 / 2 && relativeX < centerX + 18 / 2 &&
                    relativeY >= centerY - 18 / 2 && relativeY < centerY + 18 / 2) {
                return slot;
            }
        }
        return null;

    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

}