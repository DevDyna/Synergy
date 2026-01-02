package com.devdyna.synergy.init.builder.tools;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.utils.PlayerUtil;
import com.devdyna.synergy.init.builder.ItemToolTipped;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.storage.TagValueInput;

@SuppressWarnings("null")
public class SolderingGun extends ItemToolTipped {

    public SolderingGun() {
        super(new Properties().stacksTo(1),zStatic.Items.soldering_gun);
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {

        var level = c.getLevel();
        var pos = c.getClickedPos();
        var player = c.getPlayer();

        if(level.isClientSide()) return InteractionResult.FAIL;

        if (level.getBlockEntity(pos) instanceof TickingBE be
                && be instanceof AreaOfEffect aoe && c.getHand().equals(InteractionHand.MAIN_HAND)) {

            var nbt = be.saveWithFullMetadata(level.registryAccess());

            int radius = nbt.getInt(TickingBE.RADIUS).get();
            int newrange = radius;

            if (!player.isCrouching()) {
                if (aoe.radiusLimit().test(radius + 1))
                    newrange++;
                else
                    PlayerUtil.traslableActionMessage( "aoe.big", player);
            } else {
                if (aoe.radiusLimit().test(radius - 1))
                    newrange--;
                else
                    PlayerUtil.traslableActionMessage( "aoe.small", player);
            }

            if (newrange == radius)
                return InteractionResult.FAIL;

            nbt.putInt(TickingBE.RADIUS, newrange);

           

            be.loadWithComponents(TagValueInput.create(new ProblemReporter.Collector(), level.registryAccess(), nbt));

            be.setChanged();

            be.updateAOE();

            player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM);

            return InteractionResult.SUCCESS;
        }
        return super.useOn(c);
    }

}
