package com.devdyna.synergy.init.builder.survival.tank;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.beLogic.FoundryFuelProvider;
import com.devdyna.synergy.common.recipes.input.FluidInput;
import com.devdyna.synergy.common.recipes.type.FoundryFuelEfficiencyRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class FuelTankBE extends BETank implements FoundryFuelProvider {

    public FuelTankBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FuelTankBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FUEL_TANK.get(), pos, blockState);
    }

    @Override
    public boolean initConditions() {

        var f = getRecipe();

        if (f == null)
            return false;

        if (f.isEmpty())
            return false;

        return true;
    }

    @Override
    public float getSpeedModifier() {
        var f = getRecipe();
        if (f == null)
            return 1.0f;

        return (f.isEmpty() ? 1.0f : f.get().value().getSpeedModifier());
    }

    public static final int FLUID_BURN_RATE = 25;

    @Override
    public void executeOnRecipeCompleted() {

        getFluidStorage().drain((int) (FLUID_BURN_RATE * getRecipe().get().value().getUsageModifier()),
                FluidAction.EXECUTE);

    }

    private @Nullable Optional<RecipeHolder<FoundryFuelEfficiencyRecipe>> getRecipe() {

        if (level == null)
            return null;

        return level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.FOUNDRY_FUELS.getType(),
                        new FluidInput(getFluidStorage().getFluid()), level);

    }

}
