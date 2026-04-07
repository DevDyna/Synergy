package com.devdyna.synergy.init.builder.survival.steam_boiler;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.block.TickingTankBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

@SuppressWarnings("null")
public class SteamBoilerBlock extends TickingTankBlock {

    public SteamBoilerBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    public SteamBoilerBlock() {
        this(BlockBehaviour.Properties.of()
                .strength(1.0f)
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0625, 0, 0.0625, 0.9375, 1, 0.9375);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SteamBoilerBE(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof SteamBoilerBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public ItemInteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return sendFluidTooltip(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public FluidTank getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return (be instanceof SteamBoilerBE tank) ? tank.getFluidStorage() : null;
    }

    @Override
    public boolean insertFilter(FluidStack s) {
        return s.is(FluidTags.WATER);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(ID + "." + zStatic.Blocks.steam_boiler));
    }

}
