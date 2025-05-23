package com.devdyna.synergy.init.builder.ResourceGen;

import java.util.ArrayList;

import com.devdyna.synergy.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class Soil extends Block {

    public static String I0 = "landing";
    public static String I1 = "grass";
    public static String I2 = "rocky";
    public static String I3 = "desert";
    public static String I4 = "magic";
    public static String I5 = "podzol";
    public static String I6 = "mud";
    public static String I7 = "snow";
    public static String I8 = "magma";

    private TagKey<Block> block;

    /**
     * block that can generate deposits
     * 
     * @param b deposits
     */
    public Soil(TagKey<Block> b) {
        super(BlockBehaviour.Properties.of().randomTicks().sound(SoundType.ANCIENT_DEBRIS).destroyTime(Block.INDESTRUCTIBLE));
        this.block = b;
    }

    @SuppressWarnings("null")
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @SuppressWarnings("null")
    protected void randomTick(BlockState s, ServerLevel l, BlockPos p, RandomSource r) {

        ArrayList<BlockPos> positions = new ArrayList<>();
        int radius = 4;

        BlockPos.randomBetweenClosed(r, ((radius * 2 + 1) ^ 2) - 1,
                p.getX() - radius, p.getY() + 1, p.getZ() - radius,
                p.getX() + radius, p.getY() + 1, p.getZ() + radius)
                .forEach(ps -> {
                    if (l.getBlockState(ps).is(block)) {
                        positions.add(ps);
                    }
                });

        if (!positions.isEmpty() && positions.size() < (radius * 2 + 1)) {
            l.setBlockAndUpdate(p.above(),
                    LevelUtil.ResourceByTag(block,
                            r.nextInt(LevelUtil.getSizeTag(block))).defaultBlockState());
        }

    }

}
