package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.basebe.be.AreaBE;
import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

        if (server.contains("h") && server.contains("w"))
            tooltip.add(Component.translatable(ID + ".aoe")
                    .append("XZ: " + server.getInt("w") + " | Y: " + server.getInt("h"))
                    .append(server.contains("edit") && server.getBoolean("edit") ? "\nEditable with a §eSoldering Gun§7"
                            : ""));
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        var be = (AreaBE) accessor.getBlockEntity();

        if (be instanceof AreaOfEffect aoe) {
            data.putInt("h", aoe.getHeight());
            data.putInt("w", aoe.getWidth());
            data.putBoolean("edit", aoe.editalbe());
        }

    }

    @Override
    public ResourceLocation getUid() {
        return x.rl("aoe");
    }

}