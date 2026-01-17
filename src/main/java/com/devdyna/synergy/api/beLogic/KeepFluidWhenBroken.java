package com.devdyna.synergy.api.beLogic;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.SimpleFluidContent;

public interface KeepFluidWhenBroken extends SimpleFluidStorage {

    default void whenPlaced(
            Level level,
            BlockPos pos,
            @Nullable LivingEntity entity,
            ItemStack stack) {
        if (level.isClientSide())
            return;
        if (!(entity instanceof Player))
            return;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof KeepFluidWhenBroken))
            return;

        if (stack.has(zComponents.FLUID_STORAGE)) {
            SimpleFluidContent content = stack.get(zComponents.FLUID_STORAGE);
            getFluidStorage().setFluid(content.copy());
            be.setChanged();
        }
    }

    default List<ItemStack> getDropItems(
            Block block,
            BlockState state,
            Builder builder) {
        BlockEntity be = builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (!(be instanceof KeepFluidWhenBroken)) {
            return null;
        }

        if (!whenSaveContent(be, block, state, builder)) {
            return null;
        }

        ItemStack drop = new ItemStack(block);

        FluidStack fluid = getFluidStorage().getFluid();
        if (!fluid.isEmpty()) {
            drop.set(
                    zComponents.FLUID_STORAGE,
                    SimpleFluidContent.copyOf(fluid));
        }

        return List.of(drop);
    }

    boolean whenSaveContent(
            BlockEntity be,
            Block block,
            BlockState state,
            Builder builder);

    default boolean defaultSaveCondition() {
        return !getFluidStorage().getFluid().isEmpty();
    }
}
