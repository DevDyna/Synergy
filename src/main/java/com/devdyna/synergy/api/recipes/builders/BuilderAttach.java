package com.devdyna.synergy.api.recipes.builders;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.world.item.Item;

public interface BuilderAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

    public abstract RegistryLookup<Item> getProvider();

}
