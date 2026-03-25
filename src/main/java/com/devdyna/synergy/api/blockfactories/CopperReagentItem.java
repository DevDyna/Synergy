package com.devdyna.synergy.api.blockfactories;

import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings({ "deprecation", "null" })
public abstract class CopperReagentItem extends Item {

    public CopperReagentItem(Properties p) {
        super(p.stacksTo(1).durability(16));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var pos = c.getClickedPos();
        var level = c.getLevel();
        var item = c.getItemInHand();
        var state = level.getBlockState(pos);
        var player = c.getPlayer();
        var hand = c.getHand();

        var next = getNextBlock(state.getBlock());

        if (next == null || state.is(next))
            return super.useOn(c);

        if (getConfig())
            return super.useOn(c);

        player.swing(hand);

        level.setBlockAndUpdate(pos, next.withPropertiesOf(state));

        if (!player.isCreative()) {
            item.hurtAndBreak(1, player, Player.getSlotForHand(hand));
            if (item.getMaxDamage() - item.getDamageValue() == 1)
                ItemHandlerHelper.giveItemToPlayer(player, x.item(Items.GLASS_BOTTLE));
        }

        if (level.isClientSide())
            getParticles(level, pos);
        getSound(level, pos);

        return InteractionResult.SUCCESS;

    }

    public abstract Block getNextBlock(Block b);

    public abstract Boolean getConfig();

    public void getParticles(Level level, BlockPos pos) {
        LevelUtil.addCopperWaxingParticle(level, pos, ParticleTypes.COMPOSTER);
    }

    public void getSound(Level level, BlockPos pos) {
        level.playLocalSound(pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
    }

}
