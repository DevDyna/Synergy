package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities;

import java.util.Optional;

import com.devdyna.synergy.api.node_pipe.FluidNodeType;
import com.devdyna.synergy.api.node_pipe.IProvider;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBlock;
import com.devdyna.synergy.common.recipes.node_provider.ProviderInput;
import com.devdyna.synergy.common.recipes.node_provider.fluid.FluidProviderRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

@SuppressWarnings({ "null" })
public class FluidProviderBE extends NodeBaseBE
        implements IProvider<ProviderInput, FluidProviderRecipe<FluidStack>, FluidStack>, FluidNodeType {

    public FluidProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.FLUID_PROVIDER.get(), pos, blockState);
    }

    @Override
    protected void executeFluid(IFluidHandler input, IFluidHandler output) {
        var state = getBlockState();
        var dir = state.getValue(NodeBaseBlock.FACING);
        var pos = getInputPos();

        if (isValidSet(state, dir, pos, level)) {
            if (level.getGameTime() % 20 == 0)// TODO change based on upgrades
                insertFluidStacked(output, getRecipe(pos).get().value().getOutput().copy(), false);
        }

    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.FluidHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutputPos();
    }

    @Override
    public Optional<RecipeHolder<FluidProviderRecipe<FluidStack>>> getRecipe(BlockPos pos) {
        return level.getRecipeManager().getRecipeFor(
                zRecipeTypes.FLUID_PROVIDER.getType(),
                new ProviderInput(level.getBlockState(pos)),
                level);
    }

    @Override
    public FluidStack getFluidStack() {
        return getRecipe(defineInput()).isPresent() ? getRecipe(defineInput()).get().value().getOutput()
                : FluidStack.EMPTY;
    }

    @Override
    public BlockPos defineInput() {
        return getInputPos();
    }

}
