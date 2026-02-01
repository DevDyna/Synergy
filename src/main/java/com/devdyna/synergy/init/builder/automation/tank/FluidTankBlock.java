package com.devdyna.synergy.init.builder.automation.tank;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.BlockTank;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class FluidTankBlock extends BlockTank {

    public FluidTankBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    public FluidTankBlock() {
        this(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.GLASS));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FluidTankBE(pos, state);
    }

}
