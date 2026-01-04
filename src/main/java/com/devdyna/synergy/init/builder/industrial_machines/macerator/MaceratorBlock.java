package com.devdyna.synergy.init.builder.industrial_machines.macerator;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
@SuppressWarnings("null")
public class MaceratorBlock extends BaseMachineBlock {

    public MaceratorBlock(Properties p) {
        super(p);
    }

    public MaceratorBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new MaceratorBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return MaceratorBlock::new;
    }

}
