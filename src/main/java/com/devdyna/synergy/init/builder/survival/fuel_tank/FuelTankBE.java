package com.devdyna.synergy.init.builder.survival.fuel_tank;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.beLogic.FoundryFuelProvider;
import com.devdyna.synergy.api.beLogic.KeepFluidWhenBroken;
import com.devdyna.synergy.api.recipes.inputs.FluidInput;
import com.devdyna.synergy.common.recipes.foundry_fuel.FoundryFuelEfficiencyRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class FuelTankBE extends BETank implements FoundryFuelProvider, KeepFluidWhenBroken {

    public FuelTankBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FuelTankBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FUEL_TANK.get(), pos, blockState);
    }

    @Override
    public boolean whenSaveContent(BlockEntity be, Block block, BlockState state, Builder builder) {
        return defaultSaveCondition();
    }

    @Override
    public boolean initConditions() {

        var f = getRecipe();

        if (f == null)
            return false;

        if (f.isEmpty())
            return false;

        if (!f.isPresent())
            return false;

        return true;
    }

    @Override
    public float getSpeedModifier() {
        var f = getRecipe();
        return (f == null || f.isEmpty() ? 0.0f : f.get().value().getSpeedModifier());
    }

    public static final int FLUID_BURN_RATE = 25;

    @Override
    public void executeOnRecipeCompleted() {
        getFluidStorage().drain((int) (FLUID_BURN_RATE * getRecipe().get().value().getUsageModifier()),
                FluidAction.EXECUTE);
    }

    private @Nullable Optional<RecipeHolder<FoundryFuelEfficiencyRecipe>> getRecipe() {
        return level == null ? null
                : level.getRecipeManager()
                        .getRecipeFor(zRecipeTypes.FOUNDRY_FUELS.getType(),
                                new FluidInput(getFluidStorage().getFluid()), level);
    }

}
