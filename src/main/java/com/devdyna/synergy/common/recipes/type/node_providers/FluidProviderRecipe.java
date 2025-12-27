package com.devdyna.synergy.common.recipes.type.node_providers;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ProviderInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "null" })
public class FluidProviderRecipe<T> extends BaseProviderRecipe<FluidStack> {

    private final FluidStack output;

    public FluidProviderRecipe(BlockState core, @Nullable BlockState below, @Nullable BlockState left,
            @Nullable BlockState right, FluidStack output) {
        super(core, below, left, right, output);
        this.output = output;

    }

    public ItemStack assemble(ProviderInput i, HolderLookup.Provider r) {
        return x.item(this.output.getFluid().getBucket());
    }

    @Override
    public zRecipe<FluidProviderRecipe<FluidStack>> getRecipe() {
        return zRecipeTypes.FLUID_PROVIDER;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(this.output.getFluid().getBucket());
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.FLUID_PROVIDER.get().asItem();
    }

}
