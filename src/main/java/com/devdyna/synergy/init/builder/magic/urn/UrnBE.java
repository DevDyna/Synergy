package com.devdyna.synergy.init.builder.magic.urn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.NoGuiStorage;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.common.recipes.input.ItemListInput;
import com.devdyna.synergy.common.recipes.type.UrnRitualRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

@SuppressWarnings("null")
public class UrnBE extends TickingBE implements NoGuiStorage {

    private BlockCapabilityCache<ResourceHandler<ItemResource>, Direction> cache;

    public UrnBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public UrnBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.URN.get(), pos, blockState);
    }

    @Override
    public ItemStacksResourceHandler getStorage() {
        return getData(zHandlers.ITEM_STORAGE);
    }

    @Override
    public int MachineSlots() {
        return 9;
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

            if (level == null || cache == null)
                return;

        Optional<RecipeHolder<UrnRitualRecipe>> recipe = level.getServer().getRecipeManager()
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

    private void extractItems(NonNullList<Ingredient> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < cache.getCapability().size(); j++) {
                var storedItem = getStackInSlot(j);
                if (input.get(i).test(storedItem)) {
                    extract(j, 1);
                    continue;
                }
            }
        }
    }

    private List<ItemStack> getInventory() {
        List<ItemStack> ar = new ArrayList<>();
        for (int i = 0; i < cache.getCapability().size(); i++)
            for (int j = 0; j < MachineSlots(); j++)
                ar.add(cache.getCapability().getResource(i).toStack());
        return ar;
    }

}
