package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.nuclear_reactor.moderator.ModeratorBase;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum ModeratorProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();
        var check = accessor.getBlockState().getValue(BlockStateProperties.ENABLED);

        if (server.contains("fe") && server.contains("heat")) {
            if (check)
                tooltip.add(Component.literal("FE x" + server.getFloat("fe") + " | Heat x" + server.getFloat("heat")));
            else
                tooltip.add(Component.translatable(ID + ".jade.warn.moderator"));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        var bk = (ModeratorBase) accessor.getBlock();

        data.putFloat("fe", bk.FEReducer());
        data.putFloat("heat", bk.HeatReducer());

    }

    @Override
    public Identifier getUid() {
        return x.rl(zStatic.ReactorStuff.moderator);
    }

}