package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;

@SuppressWarnings({ "null" })
public class EnergyProviderBE extends NodeBaseBE {

    public EnergyProviderBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyProviderBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ENERGY_PROVIDER.get(), pos, blockState);
    }

    // TODO NEED TO BE RECREATED USING A RECIPETYPE

    // @Override
    // protected void executeItem(BlockPos inputPos, IItemHandler input, BlockPos
    // outputPos, IItemHandler output) {

    // var outState = level.getBlockState(outputPos);
    // var outBE = level.getBlockEntity(outputPos);
    // if (outBE == null)
    // return;
    // var outCap = getCapType().getCapability(level, outputPos, outState, outBE,
    // null);
    // if (outCap == null)
    // return;

    // // TODO probably it could be more optimized
    // // TODO jei

    // var direction = getBlockState().getValue(nodeType.FACING);
    // var blockGen = getBlockPos().relative(direction);
    // var belowGen = blockGen.relative(direction);

    // ArrayList<Block> conditions = new ArrayList<>();
    // ArrayList<Boolean> resultCheck = new ArrayList<>();

    // Item resulItem = level.getBlockState(blockGen).getBlock().asItem();

    // var dataHolder = new ItemStack(resulItem).getItemHolder()
    // .getData(zDataMaps.PROVIDER_RECIPES);

    // if (dataHolder != null) {

    // for (BlockState blocks : dataHolder.blocksToCheck()) {
    // conditions.add(blocks.getBlock());
    // }

    // } else {
    // resulItem = null;
    // }

    // boolean found = false;

    // if (resulItem != null) {
    // for (Block blockToCheck : conditions) {
    // for (Direction dir : Direction.values())
    // if (dir != direction && dir != direction.getOpposite()) {
    // if (level.getBlockState(blockGen.relative(dir)).is(blockToCheck)) {
    // found = true;
    // break;
    // }
    // }

    // resultCheck.add(found);
    // found = false;
    // }

    // // DONT MIX OTHER CONDITIONS OR IT WILL BREAK!
    // var finalValue = resultCheck.stream().allMatch(Boolean::booleanValue);

    // var below = dataHolder.belowBlock().isAir() ? true
    // : level.getBlockState(belowGen).is(dataHolder.belowBlock().getBlock());

    // if (finalValue && below)
    // ItemHandlerHelper.insertItemStacked((IItemHandler) outCap, new
    // ItemStack(resulItem, 1), false);

    // }
    // }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutput();
    }

}
