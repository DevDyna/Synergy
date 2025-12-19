package com.devdyna.synergy.init.machine.furnace;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.machine.core.BaseMachineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
@SuppressWarnings("null")
public class ElectricFurnaceBlock extends BaseMachineBlock {

    public ElectricFurnaceBlock(Properties p) {
        super(p);
    }

    public ElectricFurnaceBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new ElectricFurnaceBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return ElectricFurnaceBlock::new;
    }

}
