package com.devdyna.synergy.init.builder.industrial_machines.compressor;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
@SuppressWarnings("null")
public class CompressorBlock extends BaseMachineBlock {

    public CompressorBlock(Properties p) {
        super(p);
    }

    public CompressorBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new CompressorBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return CompressorBlock::new;
    }

}
