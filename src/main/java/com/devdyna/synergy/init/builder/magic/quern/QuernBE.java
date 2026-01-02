package com.devdyna.synergy.init.builder.magic.quern;

import java.util.Optional;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.common.recipes.type.QuernMillingRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

@SuppressWarnings("null")
public class QuernBE extends TickingBE implements NoGuiStorage {

    private BlockCapabilityCache<ResourceHandler<ItemResource>, Direction> cache;

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
    public ItemStacksResourceHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 1;
    }

  @Override
  protected void saveAdditional(ValueOutput output) {
      super.saveAdditional(output);
  }

  @Override
  protected void loadAdditional(ValueInput input) {
      super.loadAdditional(input);
  }

    @Override
    public void onLoad() {
        super.onLoad();
        if (this.level instanceof ServerLevel serverLevel) {
            this.cache = BlockCapabilityCache.create(
                    Capabilities.Item.BLOCK,
                    serverLevel,
                    getBlockPos(),
                    null);
        }
    }

    // public ItemStack insertItem(ItemStack stack) {
    //     return getStorage().insert(ItemResource.of(stack.getItem()), 0);
    // }

    // public ItemStack extractItem() {
        
    // }

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

        if (level == null || cache == null)
            return;

        var slot = this.cache.getCapability();

        if (slot == null)
            return;

        var item = getStackInSlot(0);

        if (!item.isEmpty()) {

            Optional<RecipeHolder<QuernMillingRecipe>> recipe = level.getServer().getRecipeManager()
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
                        LevelUtil.popItemFromPos(level, getBlockPos(), output.copy());
                        extract(0,1);
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

        if (level.getBlockState(getBlockPos()).getValue(BlockStateProperties.ENABLED)) {
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
