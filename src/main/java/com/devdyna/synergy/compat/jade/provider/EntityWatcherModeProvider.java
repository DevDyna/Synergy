package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.magic.entity_watcher.EntityWatcherBlock;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum EntityWatcherModeProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();

        if (server.contains("mode"))
            tooltip.add(Component.translatable(ID + ".jade.watcher_mode."+(server.getBoolean("mode") ? "player_only" : "not_player")));
    }

    @Override
    public void appendServerData(CompoundTag d, BlockAccessor a) {

        if (a.getBlock() instanceof EntityWatcherBlock) {
            d.putBoolean("mode", a.getBlockState().getValue(EntityWatcherBlock.PLAYER_FILTER));
        }

    }

    @Override
    public ResourceLocation getUid() {
        return x.rl(zStatic.Blocks.entity_watcher);
    }

}