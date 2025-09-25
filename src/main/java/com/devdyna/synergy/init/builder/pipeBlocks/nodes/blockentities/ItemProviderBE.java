package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import java.util.Arrays;
import java.util.Optional;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.recipeTypes.input.ProviderInput;
import com.devdyna.synergy.init.recipeTypes.type.ItemProviderRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings({ "null" })
public class ItemProviderBE extends NodeBaseBE {

    public ItemProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public ItemProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ITEM_PROVIDER.get(), pos, blockState);
    }

    @Override
    protected void executeItem(BlockPos inputPos, IItemHandler input, BlockPos outputPos, IItemHandler output) {
        var state = getBlockState();
        var dir = state.getValue(nodeType.FACING);

        if (level == null)
            return;

        Optional<RecipeHolder<ItemProviderRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.ITEM_PROVIDER.getType(),
                        new ProviderInput(level.getBlockState(inputPos)), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        var requireBelow = !(recipe.getBelow() == null || recipe.getBelow().isAir());
        var requireLeft = !(recipe.getLeft() == null || recipe.getLeft().isAir());
        var requireRight = !(recipe.getRight() == null || recipe.getRight().isAir());

        if (requireBelow)
            if (!check(inputPos.relative(dir), recipe.getBelow()))
                return;

        var dirs = Arrays.asList(Direction.values());

        dirs.removeIf(d -> d.equals(dir) || d.equals(dir.getOpposite()));

        BlockPos rightPos = null;
        BlockPos leftPos = null;

        if (requireLeft || requireRight)
            for (Direction direction : dirs) {
                if (requireRight)
                    if (check(inputPos.relative(direction), recipe.getRight())
                            && !inputPos.relative(direction).equals(leftPos)) {
                        rightPos = inputPos.relative(direction);
                        continue;
                    }

                if (requireLeft)
                    if (check(inputPos.relative(direction), recipe.getLeft())
                            && !inputPos.relative(direction).equals(rightPos)) {
                        leftPos = inputPos.relative(direction);
                        continue;
                    }
            }

        if (requireLeft && leftPos == null)
            return;
        if (requireRight && rightPos == null)
            return;

        if (level.getGameTime() % 20 == 0)
            ItemHandlerHelper.insertItemStacked(output, recipe.getOutput(), false);

    }

    private boolean check(BlockPos pos, BlockState state) {

        var cond = level.getBlockState(pos).is(state.getBlock());

        if (!state.getFluidState().isEmpty())
            cond &= state.getFluidState().isSource();

        return cond;
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.ItemHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutput();
    }

}
