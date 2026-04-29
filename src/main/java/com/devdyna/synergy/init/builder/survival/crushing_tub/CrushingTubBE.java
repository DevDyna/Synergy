package com.devdyna.synergy.init.builder.survival.crushing_tub;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.builder.survival.crushing_tub.recipe.CrushingTubRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class CrushingTubBE extends TickingBE implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage {

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    public CrushingTubBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public CrushingTubBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.CRUSHING_TUB.get(), pos, blockState);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        inv.setItem(0, getStorage().getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level instanceof ServerLevel serverLevel) {
            this.cache = BlockCapabilityCache.create(
                    Capabilities.ItemHandler.BLOCK,
                    serverLevel,
                    getBlockPos(),
                    null);
        }
    }

    public ItemStack insertItem(ItemStack stack) {
        update();
        return getStorage().insertItem(0, stack, false);
    }

    public ItemStack extractItem() {
        update();
        ItemStack extracted = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);
        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    /**
     * TODO unused but can be useful on a future automation
     */
    public ItemStack getNextDroppedItem() {
        if (level == null)
            return ItemStack.EMPTY;

        if (cache == null)
            return ItemStack.EMPTY;

        var slot = this.cache.getCapability();

        if (slot == null)
            return ItemStack.EMPTY;

        var item = slot.getStackInSlot(0);

        if (item.isEmpty())
            return ItemStack.EMPTY;

        Optional<RecipeHolder<CrushingTubRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUSHING_TUB.getType(),
                        new MonoItemInput(item), level);

        if (r.isEmpty())
            return ItemStack.EMPTY;

        var recipe = r.get().value();

        if (getFluidStorage().fill(recipe.getFluid().copy(), FluidAction.SIMULATE) != 0)
            return ItemStack.EMPTY;

        return level.random.nextFloat() < recipe.getOutput().chance() ? recipe.getOutput().item().copy()
                : ItemStack.EMPTY;
    }

    public void craft(boolean dropWhenCrafted) {

        if (level == null)
            return;

        if (cache == null)
            return;

        var slot = this.cache.getCapability();

        if (slot == null)
            return;

        var item = slot.getStackInSlot(0);

        update();

        if (item.isEmpty())
            return;

        Optional<RecipeHolder<CrushingTubRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUSHING_TUB.getType(),
                        new MonoItemInput(item), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (getFluidStorage().fill(recipe.getFluid().copy(), FluidAction.SIMULATE) == 0)
            return;

        getFluidStorage().fill(recipe.getFluid().copy(), FluidAction.EXECUTE);
        getStorage().extractItem(0, 1, false);

        if (level.random.nextFloat() < recipe.getOutput().chance())
            if (dropWhenCrafted)
                Block.popResource(level, getBlockPos().above(), recipe.getOutput().item().copy());

        level.playSound(null, getBlockPos(),
                LevelUtil.chance(50, level) ? SoundEvents.SLIME_BLOCK_FALL : SoundEvents.SNIFFER_EGG_CRACK,
                SoundSource.BLOCKS, 1f, 1f);

        update();
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    @Override
    public int getFluidCapacity() {
        return 16_000;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        tag.put("inventory", getStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
        getStorage().deserializeNBT(registries, tag.getCompound("inventory"));
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

}
