package com.devdyna.synergy.init.builder.automation.chopper;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.MachineBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class ChopperBlock extends MachineBlock {

    public ChopperBlock() {
        this(Properties.of().forceSolidOn().sound(SoundType.STONE).strength(1.0f).mapColor(MapColor.TERRACOTTA_ORANGE));
    }

    public ChopperBlock(Properties p) {
        super(p);
    }

    @Override
    protected InteractionResult onClickAction(BlockState state, Level level, BlockPos pos, Player player) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(zItems.CONFIGURATOR) ? InteractionResult.PASS : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new ChopperBE(p, s);
    }

    @Override
    protected Function<Properties, Block> getFactory() {
        return ChopperBlock::new;
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.chopper));
    }
}
