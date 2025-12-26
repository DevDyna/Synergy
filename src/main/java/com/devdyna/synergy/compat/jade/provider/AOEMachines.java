package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.beLogic.SimpleAOE;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum AOEMachines implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();

        if (server.contains("xz"))
            tooltip.add(Component.translatable(ID + ".aoe")
                    .append(server.contains("isComplex") && server.getBoolean("isComplex").isPresent() && server.getBoolean("isComplex").get() && server.contains("h")
                            ? ("XZ: " + server.getInt("xz").get() + " | Y: " + server.getInt("h").get())
                            : "" + server.getInt("xz").get()));

    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        var be = (TickingBE) accessor.getBlockEntity();

        data.putBoolean("isComplex", be instanceof AreaOfEffect);

        if (be instanceof SimpleAOE simpleAOE)
            data.putInt("xz", simpleAOE.radius());

        if (be instanceof AreaOfEffect aoe)
            data.putInt("h", aoe.height());

    }

    @Override
    public Identifier getUid() {
        return x.rl("aoe");
    }

}