package com.devdyna.synergy.init.builder.automation.router;

import static com.devdyna.synergy.Main.ID;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.BlockStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class RouterBlock extends BlockStorage {

    public RouterBlock(Properties properties) {
        super(properties);
    }

    public RouterBlock() {
        this(Properties.of().sound(SoundType.WOOD).mapColor(MapColor.COLOR_GRAY).strength(0.4f));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos arg0, BlockState arg1) {
        return new RouterBE(arg0, arg1);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return RouterBlock::new;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
                super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
         tooltipComponents.add(Component.translatable(ID + "." + zStatic.Blocks.router));
    }

}
