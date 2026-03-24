package com.devdyna.synergy.init.builder.magic.quern;

import java.util.Optional;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.builder.magic.quern.recipe.QuernMillingRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public class QuernBE extends TickingBE implements ItemStorageBlock , NoGuiStorage{

    private BlockCapabilityCache<IItemHandler, Direction> cache;

    private float rotation = 0f; // client & server rotation
    private float speed = 0f; // server authoritative speed

    private int minDelay;

    private static final float MAX_SPEED = 10f; // degrees per tick
    private static final float ACCEL = 0.1f;
    private static final float DECEL = 0.1f;

    public QuernBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public QuernBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.QUERN.get(), pos, blockState);
    }

    @Override
    public ItemStackHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getStorage().getSlots());
        inv.setItem(0, getStorage().getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(tag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(tag, pRegistries);
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
        return getStorage().insertItem(0, stack, false);
    }

    public ItemStack extractItem() {
        ItemStack extracted = getStorage().extractItem(0, getStorage().getStackInSlot(0).getCount(), false);
        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
    }

    public float getRotation(float partialTicks) {
        return rotation + speed * partialTicks;
    }

    @Override
    public void tickServer() {

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            minDelay++;
        } else {
            minDelay = 0;
        }

        if (cache == null)
            return;

        var slot = this.cache.getCapability();

        if (slot == null)
            return;

        var item = slot.getStackInSlot(0);

        if (!item.isEmpty()) {

            Optional<RecipeHolder<QuernMillingRecipe>> recipe = level.getRecipeManager()
                    .getRecipeFor(zRecipeTypes.QUERN_MILLING.getType(),
                            new MonoItemInput(item), level);

            var flag = !recipe.isEmpty();

            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState().setValue(BlockStateProperties.ENABLED, flag));

            if (LevelUtil.chance(75, level))
                if (flag) {

                    if (level.getGameTime() % 15 + (LevelUtil.chance(50, level) ? 0 : 5) == 0) {
                        level.playSound(null, getBlockPos(),
                                SoundEvents.GRINDSTONE_USE,
                                SoundSource.BLOCKS, 0.25F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);
                    }

                    if (minDelay >= recipe.get().value().getTime() && minDelay % recipe.get().value().getTime() == 0) {
                        var output = recipe.get().value().getOutput();
                        dropInWorldResult(output, level, getBlockPos());
                        slot.extractItem(0, 1, false);
                        level.playSound(null, getBlockPos(),
                                SoundEvents.ITEM_FRAME_REMOVE_ITEM,
                                SoundSource.BLOCKS, 0.5F * (LevelUtil.chance(50, level) ? 1f : 0.75f), 1);
                        setChanged(level, getBlockPos(), getBlockState());
                    }
                }

        } else {
            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState().setValue(BlockStateProperties.ENABLED, false));
        }

    }

    @Override
    public void tickClient() {

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            if (speed < MAX_SPEED)
                speed += ACCEL;
        } else {
            if (speed > 0) {
                speed -= DECEL;

            }
            if (speed < 0)
                speed = 0;
        }

        rotation += speed;
        if (rotation >= 360f)
            rotation -= 360f;
    }

}
