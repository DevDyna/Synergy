package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.EnergyProvider;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.automation.solar_panel.SolarPanelBE;
import com.devdyna.synergy.init.builder.laser.laser_rotor.LaserRotorBE;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBE;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum EnergyTipProviders implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();

        if (!server.contains("fe"))
            return;

        var fe = server.getInt("fe");
        if (server.contains("heat")) {
            var heat = server.getDouble("heat");
            tooltip.add(
                    Component.literal("§7")
                            .append(Component.translatable(ID + ".heat"))
                            .append(heat + "°/t | ")
                            .withStyle(heat > 0 ? ChatFormatting.RED : ChatFormatting.GREEN)
                            .append(Component.translatable(ID + ".fe")
                                    .append(fe + "fe/t")));
        }

        if (server.contains("extra")) {
            tooltip.add(Component.literal(StringUtil.getFormat().format(fe) + "FE/tick ")
                    .append(Component.translatable(ID + ".jade.tip." + server.getString("extra"))));
        }

    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        var be = (TickingBE) accessor.getBlockEntity();

        if (be instanceof EnergyProvider gen)
            data.putInt("fe", gen.getFERate());

        if (be instanceof LaserRotorBE)
            data.putString("extra", "cycle");

        if (be instanceof SolarPanelBE)
            data.putString("extra", "daytime");

        if (be instanceof ReactorControllerBE reactorControllerBE)
            data.putDouble("heat", reactorControllerBE.getHeat());

    }

    @Override
    public ResourceLocation getUid() {
        return x.rl("fegen");
    }

}