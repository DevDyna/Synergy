package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.nuclear_reactor.cooler.CoolerBlockBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum CoolerProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();
        var check = accessor.getBlockState().getValue(BlockStateProperties.ENABLED);

        tooltip.add(Component.translatable(ID+".jade.info.cooler_status."+check));

        if (server.contains("base") && server.contains("active"))
            tooltip.add(
                    Component.literal("Cooling: "+ (check
                                    ? server.getFloat("active")
                                    : server.getFloat("base"))));

    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        var bk = (CoolerBlockBase) accessor.getBlock();

        data.putFloat("base", bk.getBaseCooling());
        data.putFloat("active", bk.getActiveCooling());

    }

    @Override
    public Identifier getUid() {
        return x.rl(zStatic.ReactorStuff.cooler);
    }

}