package com.devdyna.synergy.compat.jei.categories.core;

import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;

import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BaseMachineRecipeCategory<R extends BaseMachineRecipeType<?>> extends BaseRecipeCategory<R> {

protected IDrawableAnimated arrow;

    public BaseMachineRecipeCategory(IGuiHelper h) {
        super(h);
    }

    public abstract MachineType<? extends Block, ? extends BlockEntity, ? extends AbstractContainerMenu, ? extends Recipe<?>> getMachine();

    @Override
    public String getTitleKey() {
        return "machine." + getMachine().id();
    }

    @Override
    public ItemLike getIconItem() {
        return (Item) getMachine().item().get();
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/" + getMachine().id() + ".png";
    }

}
