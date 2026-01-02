package com.devdyna.synergy.init.builder.tools;

import java.util.function.Consumer;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.transfer.transaction.Transaction;

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
                    try (var tx = Transaction.openRoot()) {
                        c.getItemInHand().set(zComponents.FE_STORED,
                                data.extract(Math.min(capacity, data.getAmountAsInt()), tx));
                        tx.commit();
                    }

                    c.getPlayer().swing(c.getHand());
                    c.getLevel().playSound(c.getPlayer(), c.getClickedPos(), SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                            SoundSource.PLAYERS, 1F, 2F);
                }

            } else {
                if (((EnergyBlock) be).canReceive()) {
                    var data = be.getData(zHandlers.ENERGY_STORAGE);
                    if (data.getCapacityAsInt() - data.getAmountAsInt() != 0) {

                        try (var tx = Transaction.openRoot()) {

                            var item = c.getItemInHand().get(zComponents.FE_STORED).intValue();
                            var extraction = Math.min(data.insert(item, tx), item);
                            tx.commit();

                            c.getItemInHand().set(zComponents.FE_STORED, item - extraction);
                        }

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
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
            Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".tip"));

        tooltipAdder.accept(Component.translatable(Main.ID + "." + zStatic.Items.Batteries.TYPE_BATTERY + ".energy")
                .append(Component.literal(
                        (stack.get(zComponents.FE_STORED) == null ? "0"
                                : (flag.hasShiftDown() ? stack.get(zComponents.FE_STORED).intValue()
                                        : StringUtil.getFormat().format(stack.get(zComponents.FE_STORED).intValue()))
                                        + "")
                                + "/"
                                + (flag.hasShiftDown() ? capacity : StringUtil.getFormat().format(capacity)))
                        .withStyle(ChatFormatting.RED)));
    }

}
