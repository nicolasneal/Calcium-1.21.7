package net.nicolas.calcium.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.sound.SoundEvents;

public class ModFoods {

    public static final FoodComponent CHEVAL = new FoodComponent.Builder().nutrition(3).saturationModifier(1.6f).build();
    public static final FoodComponent COOKED_CHEVAL = new FoodComponent.Builder().nutrition(7).saturationModifier(10.6f).build();
    public static final FoodComponent BEAR = new FoodComponent.Builder().nutrition(3).saturationModifier(1.4f).build();
    public static final FoodComponent COOKED_BEAR = new FoodComponent.Builder().nutrition(8).saturationModifier(10.2f).build();
    public static final FoodComponent CAMEL = new FoodComponent.Builder().nutrition(3).saturationModifier(1.8f).build();
    public static final FoodComponent COOKED_CAMEL = new FoodComponent.Builder().nutrition(7).saturationModifier(11.0f).build();
    public static final FoodComponent CHEVON = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2f).build();
    public static final FoodComponent COOKED_CHEVON = new FoodComponent.Builder().nutrition(6).saturationModifier(9.6f).build();
    public static final FoodComponent FROG = new FoodComponent.Builder().nutrition(2).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FROG = new FoodComponent.Builder().nutrition(4).saturationModifier(8.0f).build();
    public static final FoodComponent SQUID = new FoodComponent.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static final FoodComponent CALAMARI = new FoodComponent.Builder().nutrition(5).saturationModifier(6.2f).build();
    public static final FoodComponent WATER_BOWL = new FoodComponent.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().build();

}
