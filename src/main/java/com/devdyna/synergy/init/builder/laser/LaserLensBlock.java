package com.devdyna.synergy.init.builder.laser;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class LaserLensBlock extends Block implements IBlockLaser {

    public LaserLensBlock() {
        super(getProperties);
    }

    @Override
    public boolean isPathfindable() {
        return isPathfindable();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape();
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    //         TooltipFlag f) {
    //     t.add(Component.translatable(Main.ID + "." + zStatic.Lazers.lens));
    // }

}
