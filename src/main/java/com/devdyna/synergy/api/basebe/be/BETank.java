package com.devdyna.synergy.api.basebe.be;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.beLogic.KeepInventory;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;

@SuppressWarnings("null")
public abstract class BETank extends BlockEntity implements SimpleFluidStorage, KeepInventory {

    public BETank(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    @Override
    public int getFluidCapacity() {
        return 1000;
    }

    @Override
    public void loadCustomOnly(CompoundTag nbt, RegistryAccess registryAccess) {
        super.loadCustomOnly(nbt, registryAccess);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
        super.loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public boolean whenSaveContent(BlockEntity be, Block block, BlockState state, Builder builder) {
        return !getFluidStorage().getFluid().isEmpty();
    }

}
