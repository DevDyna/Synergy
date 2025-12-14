package com.devdyna.synergy.init.builder.urn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.coreBE.be.TickingBE;
import com.devdyna.synergy.common.recipeTypes.input.ItemListInput;
import com.devdyna.synergy.common.recipeTypes.type.UrnRitualRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class UrnBE extends TickingBE implements ItemStorageBlock , NoGuiStorage{

    private BlockCapabilityCache<IItemHandler, Direction> cache;

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
        return 9;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        // pTag.put("inventory", getStorage().serializeNBT(pRegistries));
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        // getStorage().deserializeNBT(pRegistries, pTag.getCompound("inventory"));
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
        ItemStack remaining = stack;
        for (int i = 0; i < getStorage().getSlots(); i++) {
            remaining = getStorage().insertItem(i, remaining, false);
            if (remaining.isEmpty())
                break;
        }
        return remaining;
    }

    public ItemStack extractItem() {
        for (int i = 0; i < getStorage().getSlots(); i++) {
            ItemStack extracted = getStorage().extractItem(i, getStorage().getStackInSlot(i).getCount(), false);
            if (!extracted.isEmpty()) {
                return extracted;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void tickClient() {
        var pos = getBlockPos();
        if (LevelUtil.chance(25, level))
            level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.65, pos.getZ() + 0.5,
                    0, 0, 0);
    }

    @Override
    public void tickServer() {

        if (!getInventory().isEmpty()
                && LevelUtil.chance(75, level)
                && level.getGameTime() % 20 == 0)
            if (level != null) {

                Optional<RecipeHolder<UrnRitualRecipe>> recipe = level.getRecipeManager()
                        .getRecipeFor(zRecipeTypes.URN_RITUAL_RECIPE.getType(),
                                new ItemListInput(getInventory()), level);

                if (!recipe.isEmpty()) {
                    var input = recipe.get().value().getIngredients();
                    var output = recipe.get().value().getResultItem();
                    LevelUtil.popItemFromPos(level, getBlockPos(), output.copy());

                    extractItems(input);

                    if (LevelUtil.chance(25, level))
                        level.playSound(null, getBlockPos(),
                                SoundEvents.BREWING_STAND_BREW,
                                SoundSource.BLOCKS, 0.5F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);

                    setChanged(level, getBlockPos(), getBlockState());

                }
            }

    }

    private void extractItems(NonNullList<Ingredient> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < getStorage().getSlots(); j++) {
                var storedItem = getStorage().getStackInSlot(j);
                if (input.get(i).test(storedItem)) {
                    getStorage().extractItem(j, 1, false);
                    continue;
                }
            }
        }
    }

    private List<ItemStack> getInventory() {
        List<ItemStack> items = new ArrayList<>();
        IItemHandler cap = this.cache.getCapability();
        if (cap != null)
            for (int i = 0; i < cap.getSlots(); i++) {
                if (!cap.getStackInSlot(i).isEmpty())
                    items.add(cap.getStackInSlot(i));
            }
        return items;
    }

}
