package com.devdyna.synergy.init.builder.tools;

import java.util.List;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBE;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class SolderingGun extends Item {

    public SolderingGun() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {

        var level = c.getLevel();
        var pos = c.getClickedPos();
        var player = c.getPlayer();

        if (level.getBlockEntity(pos) instanceof ReactorControllerBE be
                && c.getHand().equals(InteractionHand.MAIN_HAND)) {

            var nbt = be.saveWithFullMetadata(level.registryAccess());

            int radius = nbt.getInt(ReactorControllerBE.RADIUS);
            int newrange = radius;

            if (!player.isCrouching()) {
                if (be.getRange().test(radius + 1))
                    newrange++;
            } else {
                if (be.getRange().test(radius - 1))
                    newrange--;
            }

            if (newrange == radius)
                return InteractionResult.FAIL;

            nbt.putInt(ReactorControllerBE.RADIUS, newrange);

            be.loadWithComponents(nbt, level.registryAccess());

            be.setChanged();

            player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM);

            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.useOn(c);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(Main.ID + "." + zStatic.Items.soldering_gun));
    }
}
