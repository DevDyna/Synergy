package com.devdyna.synergy.api.beLogic;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public interface KeepInventory {

    default void whenPlaced(Level level, BlockPos pos, @Nullable LivingEntity entity,
            ItemStack stack) {
        if (entity instanceof Player && !level.isClientSide()) {
            var be = level.getBlockEntity(pos);

            if (be instanceof BlockEntity && be instanceof KeepInventory keep)
                if (stack.has(zComponents.MACHINE_DATA)) {
                    CompoundTag nbt = stack.get(zComponents.MACHINE_DATA).copyTag();
                    if (!nbt.isEmpty())
                        keep.loadCustomOnly(nbt, level.registryAccess());
                }
        }
    }

    /**
     * return Null when fail
     */
    default List<ItemStack> getDropItems(Block block, BlockState state, Builder builder) {
        var be = builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (be instanceof BlockEntity && be instanceof KeepInventory keep
                && whenSaveContent(be, block, state, builder)) {
            CompoundTag nbt = new CompoundTag();
            ItemStack item = x.item(block);
            keep.saveAdditional(nbt, builder.getLevel().registryAccess());
            if (!nbt.isEmpty())
                item.set(zComponents.MACHINE_DATA, CustomData.of(nbt));

            return List.of(item);
        }
        return null;
    }

    abstract void saveAdditional(CompoundTag tag, HolderLookup.Provider registries);

    abstract void loadCustomOnly(CompoundTag nbt, RegistryAccess registryAccess);

    abstract boolean whenSaveContent(BlockEntity be, Block block, BlockState state, Builder builder);
}
