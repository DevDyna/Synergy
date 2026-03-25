package com.devdyna.synergy.init.builder.tools;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.blockfactories.CopperReagentItem;
import com.devdyna.synergy.api.utils.LevelUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;

public class HoneySolution extends CopperReagentItem {

    public HoneySolution(Properties p) {
        super(p);
    }

    public HoneySolution() {
        super(new Item.Properties());
    }

    @Override
    public Block getNextBlock(Block b) {
        return DataMapHooks.getBlockWaxed(b);
    }

    @Override
    public Boolean getConfig() {
        return Common.DISABLE_HONEY_SOLUTION_EVENT.get();
    }

    public void getParticles(Level level, BlockPos pos) {
        LevelUtil.addCopperWaxingParticle(level, pos, ParticleTypes.WAX_ON);
    }

    public void getSound(Level level, BlockPos pos) {
        level.playLocalSound(pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F, false);
    }

}
