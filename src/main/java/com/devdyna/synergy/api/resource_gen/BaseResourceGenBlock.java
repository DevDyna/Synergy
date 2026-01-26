package com.devdyna.synergy.api.resource_gen;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public abstract class BaseResourceGenBlock extends TickingBlock {

    public BaseResourceGenBlock(Properties properties) {
        super(properties.strength(1.0f).destroyTime(1.0f).sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    }

    public MutableComponent fluid(IntValue amount, FluidStack fluid, IntValue tickrate) {
        return tip(amount.get() + " mb", fluid.getFluidType().getDescription().getString(), tickrate.get());
    }

    public MutableComponent item(IntValue amount, Item item, IntValue tickrate) {
        return tip(amount.get() + "x", item.getDescription().getString(), tickrate.get());
    }

    public MutableComponent tip(String amount, String item, int tickrate) {
        return Component.translatable(ID + ".resourcegen.tip", amount, item, tickrate).withStyle(ChatFormatting.GRAY);
    }

    public MutableComponent water(IntValue amount, IntValue tickrate) {
        return fluid(amount, x.fluid(Fluids.WATER).copy(), tickrate);
    }

    public MutableComponent cobble(IntValue amount, IntValue tickrate) {
        return item(amount, Items.COBBLESTONE, tickrate);
    }

}
