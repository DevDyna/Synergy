package com.devdyna.synergy.init.builder.tools;

import java.util.List;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.utils.StringUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class Battery extends Item {

    private int capacity;

    public Battery(int capacity) {
        super(new Properties().stacksTo(1).component(zComponents.FE_STORED, null));
        this.capacity = capacity;
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var be = c.getLevel().getBlockEntity(c.getClickedPos());
        if (be != null) {

            if (c.getItemInHand().get(zComponents.FE_STORED) == null
                    || c.getItemInHand().get(zComponents.FE_STORED).intValue() == 0) {

                if (((EnergyBlock) be).canExtract()) {
                    var data = be.getData(zHandlers.ENERGY_STORAGE);
                    c.getItemInHand().set(zComponents.FE_STORED,
                            data.extractEnergy(Math.min(capacity, data.getEnergyStored()), false));
                    c.getPlayer().swing(c.getHand());
                    c.getLevel().playSound(c.getPlayer(), c.getClickedPos(), SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                            SoundSource.PLAYERS, 1F, 2F);
                }

            } else {
                if (((EnergyBlock) be).canReceive()) {
                    var data = be.getData(zHandlers.ENERGY_STORAGE);
                    if (data.getMaxEnergyStored() - data.getEnergyStored() != 0) {
                        var item = c.getItemInHand().get(zComponents.FE_STORED).intValue();
                        var extraction = Math.min(data.receiveEnergy(item, true), item);
                        data.receiveEnergy(extraction, false);

                        c.getItemInHand().set(zComponents.FE_STORED, item - extraction);
                        c.getPlayer().swing(c.getHand());
                        c.getLevel().playSound(c.getPlayer(), c.getClickedPos(), SoundEvents.WOODEN_BUTTON_CLICK_ON,
                                SoundSource.PLAYERS, 1F, 2F);
                    }

                }

            }

        }

        return super.useOn(c);
    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".tip"));

        t.add(Component.translatable(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".energy")
                .append(Component.literal(
                        (i.get(zComponents.FE_STORED) == null ? "0"
                                : (f.hasShiftDown() ? i.get(zComponents.FE_STORED).intValue()
                                        : StringUtil.getFormat().format(i.get(zComponents.FE_STORED).intValue())) + "")
                                + "/"
                                + (f.hasShiftDown() ? capacity : StringUtil.getFormat().format(capacity)))
                        .withStyle(ChatFormatting.RED)));
    }
}
