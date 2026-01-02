package com.devdyna.synergy.init.builder.magic.void_box;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.beLogic.DropOnBreak;
import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.common.recipes.type.VoidBoxInfusionRecipe;
import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.awt.Color;

@SuppressWarnings("null")
public class VoidBoxBlock extends TickingBlock {

    public VoidBoxBlock(Properties properties) {
        super(properties
                .strength(0.4f)
                .destroyTime(0.4f)
                .sound(SoundType.SCULK)
                .mapColor(MapColor.DEEPSLATE));
    }

    public VoidBoxBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VoidBoxBE(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED, BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(BlockStateProperties.ENABLED, true)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(4, 0, 4, 12, 8, 12);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof DropOnBreak be)
            be.drops();
        super.destroy(level, pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof VoidBoxBE be) {

            Optional<RecipeHolder<VoidBoxInfusionRecipe>> r = level.getServer().getRecipeManager()
                    .getRecipeFor(zRecipeTypes.VOID_BOX_INFUSION.getType(),
                            new MonoItemInput(stack), level);

            if (!r.isEmpty()) {

                var recipe = r.get().value();
                if (!level.isClientSide())
                    LevelUtil.addParticle(ParticleTypes.WITCH, level, pos, true);
                level.playSound(player, pos, SoundEvents.WITCH_DRINK, SoundSource.BLOCKS, 0.5f, 0.25f);
                LevelUtil.popItemFromPos(level, pos, recipe.getOutput().copy());

                if (!player.isCreative())
                    stack.shrink(1);

                return InteractionResult.SUCCESS_SERVER;
            } else if (!stack.is(zItemTag.VOID_BOX_DENY))
                return be.itemUseOn(player, level, pos, hand);

        }
        return InteractionResult.FAIL;
    }

    @Override
    public MutableComponent getName() {
        var level = Minecraft.getInstance().level;
        int color = (level == null ? Color.BLUE.getRGB()
                : ColorUtil.pulseColor(level, Color.BLUE.getRGB(), Color.MAGENTA.getRGB()));
        return Component.translatable(this.getDescriptionId()).withColor(color);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {

    // t.clear();

    // var level = Minecraft.getInstance().level;
    // int color = (level == null ? Color.BLUE.getRGB()
    // : ColorUtil.pulseColor(level, Color.BLUE.getRGB(), Color.MAGENTA.getRGB()));
    // t.add(0, Component.translatable(this.getDescriptionId()).withColor(color));

    // t.add(Component.translatable(Main.ID + "." + zStatic.Blocks.void_box));
    // }

}
