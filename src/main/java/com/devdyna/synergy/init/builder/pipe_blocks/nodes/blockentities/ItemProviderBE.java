package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities;

import java.util.Optional;

import com.devdyna.synergy.api.node_pipe.IProvider;
import com.devdyna.synergy.api.node_pipe.ItemNodeType;
import com.devdyna.synergy.api.node_pipe.nodeType;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.common.recipes.input.ProviderInput;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings({ "null" })
public class ItemProviderBE extends NodeBaseBE
        implements IProvider<ProviderInput, ItemProviderRecipe<ItemStack>, ItemStack>, ItemNodeType {

    public ItemProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_PROVIDER.get(), pos, blockState);
    }

    @Override
    protected void executeItem(IItemHandler input, IItemHandler output) {

        var state = getBlockState();
        var dir = state.getValue(nodeType.FACING);
        var pos = getInputPos();

        if (isValidSet(state, dir, pos, level)) {
            if (level.getGameTime() % 20 == 0)// TODO change based on upgrades
                insertItemStacked(output, getRecipe(pos).get().value().getOutput().copy(), false);
        }

    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.ItemHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutputPos();
    }

    @Override
    public Optional<RecipeHolder<ItemProviderRecipe<ItemStack>>> getRecipe(BlockPos pos) {
        return level.getRecipeManager().getRecipeFor(
                zRecipeTypes.ITEM_PROVIDER.getType(),
                new ProviderInput(level.getBlockState(pos)),
                level);
    }

    @Override
    public ItemStack getItemStack() {
        return getRecipe(defineInput()).isPresent() ? getRecipe(defineInput()).get().value().getOutput()
                : ItemStack.EMPTY;
    }

    @Override
    public BlockPos defineInput() {
        return getInputPos();
    }
}
