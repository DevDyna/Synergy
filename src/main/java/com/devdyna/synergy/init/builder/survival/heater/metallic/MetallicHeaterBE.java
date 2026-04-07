package com.devdyna.synergy.init.builder.survival.heater.metallic;

import com.devdyna.synergy.api.beLogic.FoundryFuelProvider;
import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class MetallicHeaterBE extends SolidFuelHeaterBE
        implements FoundryFuelProvider {

    public MetallicHeaterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public MetallicHeaterBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.METALLIC_HEATER.get(), pos, blockState);
    }

    @Override
    public float getSpeedModifier() {
        return (heat - 20) * 0.0125f;
    }

    @Override
    public boolean initConditions() {
        return getBlockState().getValue(BlockStateProperties.ENABLED);
    }

    @Override
    public void executeOnRecipeCompleted() {

    }

    @Override
    public int getHeatCap() {
        return 300;
    }

    @Override
    protected float heatMultiplier() {
        return 2.0f;
    }

}
