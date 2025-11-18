package net.nicolas.calcium.mixin.attributes;

import net.minecraft.entity.passive.AxolotlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AxolotlEntity.class)
public class Axolotl {

    @ModifyConstant(
        method = "createAxolotlAttributes",
        constant = @Constant(doubleValue = 14.0)
    )
    private static double setAxolotlHealth(double originalHealth) {
        return 6.0;
    }

}