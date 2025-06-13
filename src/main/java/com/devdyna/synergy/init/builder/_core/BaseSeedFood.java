package com.devdyna.synergy.init.builder._core;

import com.devdyna.synergy.init.Material;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class BaseSeedFood extends ItemNameBlockItem {

    public BaseSeedFood(Block block, int nutrition,
            float saturationModifier) {
        super(block, Material.iProp.food((new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturationModifier).build()));
    }

    public BaseSeedFood(Block block, int nutrition,
            float saturationModifier, boolean fastToEat) {
        super(block, Material.iProp.food((new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturationModifier).fast().build()));
    }

    public BaseSeedFood(Block block, int nutrition,
            float saturationModifier, boolean fastToEat, boolean isAlwaysEdible) {
        super(block, Material.iProp.food((new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturationModifier).fast().alwaysEdible().build()));
    }

}
