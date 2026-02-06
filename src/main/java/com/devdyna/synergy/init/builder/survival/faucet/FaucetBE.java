package com.devdyna.synergy.init.builder.survival.faucet;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

@SuppressWarnings("null")
public class FaucetBE extends TickingBE {

    public FaucetBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.FAUCET.get(), pos, state);
    }

    Direction inputDir = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

    @Override
    public void tickBoth() {

        if (level == null)
            return;

        IFluidHandler in = Capabilities.FluidHandler.BLOCK.getCapability(level,
                getBlockPos().relative(inputDir),
                level.getBlockState(getBlockPos().relative(inputDir)),
                level.getBlockEntity(getBlockPos().relative(inputDir)),
                inputDir.getOpposite());

        IFluidHandler out = Capabilities.FluidHandler.BLOCK.getCapability(level,
                getBlockPos().below(),
                level.getBlockState(getBlockPos().below()),
                level.getBlockEntity(getBlockPos().below()), Direction.UP);

        if (in == null || out == null)
            return;

        if (level.hasNeighborSignal(getBlockPos()) && canActivate())
            set(true);

        if (status()) {

            var moved = FluidUtil.tryFluidTransfer(out, in, 25, true);

            // LogUtil.info("m " + moved);

            if (moved.isEmpty())
                set(false);
        }
    }

    public boolean canActivate() {
        // if (level == null)
        // return false;

        IFluidHandler in = Capabilities.FluidHandler.BLOCK.getCapability(level,
                getBlockPos().relative(inputDir),
                level.getBlockState(getBlockPos().relative(inputDir)),
                level.getBlockEntity(getBlockPos().relative(inputDir)),
                inputDir.getOpposite());

        IFluidHandler out = Capabilities.FluidHandler.BLOCK.getCapability(level,
                getBlockPos().below(),
                level.getBlockState(getBlockPos().below()),
                level.getBlockEntity(getBlockPos().below()), Direction.UP);

        if (in == null || out == null)
            return false;

        // LogUtil.info("res " + !FluidUtil.tryFluidTransfer(out, in, 25, false).isEmpty());

        return !FluidUtil.tryFluidTransfer(out, in, 25, false).isEmpty();
    }

    public boolean status() {
        return getBlockState().getValue(BlockStateProperties.ENABLED);
    }

    public void set(boolean b) {
        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ENABLED, b));
    }

    public ItemInteractionResult onClick() {
        // LogUtil.info("click try");
        if (canActivate()) {
            set(true);
            // LogUtil.info("click success");
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(net.minecraft.core.HolderLookup.Provider provider) {
        return saveWithoutMetadata(provider);
    }
}
