package com.devdyna.synergy.init.builder.urn;

import java.util.Optional;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.recipeTypes.type.UrnRitualRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class UrnBE extends BaseBE implements ItemStorageBlock {

    public UrnBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public UrnBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.URN.get(), pos, blockState);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        for (int i = 0; i < getStorage().getSlots(); i++)
            inv.setItem(i, getStorage().getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", getStorage().serializeNBT(pRegistries));
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        getStorage().deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    @Override
    public void tickServer() {
        if (!getStorage().getStackInSlot(0).isEmpty() && LevelUtil.chance(90, level) && level.getGameTime() % 20 == 0)
            if (level != null) {

                Optional<RecipeHolder<UrnRitualRecipe>> recipe = level.getRecipeManager()
                        .getRecipeFor(zRecipeTypes.URN_RITUAL_RECIPE.getType(),
                                new MonoItemInput(getStorage().getStackInSlot(0)), level);

                if (!recipe.isEmpty()) {
                    var input = recipe.get().value().getInput();
                    var output = recipe.get().value().getOutput();
                    if (getStorage().getStackInSlot(0).getCount() >= input.getCount()) {
                        LevelUtil.popItemFromPos(level, getBlockPos(), output.copy());
                        getStorage().extractItem(0, input.getCount(), false);
                        level.playSound(null, getBlockPos(),
                                SoundEvents.ITEM_FRAME_REMOVE_ITEM,
                                SoundSource.BLOCKS, 1F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);
                        setChanged(level, getBlockPos(), getBlockState());
                    }
                }
            }

    }

}
