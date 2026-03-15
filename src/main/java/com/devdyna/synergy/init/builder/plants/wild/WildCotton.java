package com.devdyna.synergy.init.builder.plants.wild;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.api.blockfactories.plants.CropEntityInteraction;
import com.devdyna.synergy.api.blockfactories.plants.builder.BaseWildCropBlock;
import com.devdyna.synergy.init.types.zBlockTag;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class WildCotton extends BaseWildCropBlock implements CropEntityInteraction {

    public WildCotton() {
        super(Properties.of().mapColor(MapColor.WOOL));
    }

    @Override
    public TagKey<Block> getSpawnFilter() {
        return zBlockTag.CAN_SUSTAIN_COTTON;
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        getEntityInside(state, level, pos, entity);
    }

    @Override
    public Vec3 speedFactor() {
        return new Vec3(0.9, 0.8, 0.9);
    }

    @Override
    public boolean HurtWhenInside() {
        return false;
    }

    @Override
    public boolean HurtWhenStep() {
        return false;
    }

    @Override
    public boolean StuckWhenInside() {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID +".disabled"));
    }

}
