package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBE;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum ReactorControllerProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
        INSTANCE;

        @Override
        public void appendTooltip(
                        ITooltip tooltip,
                        BlockAccessor accessor,
                        IPluginConfig config) {

                var server = accessor.getServerData();

                if (server.contains("fe") && server.contains("heat")) {

                        var heat = server.getDouble("heat");
                        var fe = server.getInt("fe");

                        tooltip.add(
                                        Component.literal("§7")
                                                        .append(Component.translatable(ID + ".heat"))
                                                        .append(heat + "°/t | ")
                                                        .withStyle(heat > 0 ? ChatFormatting.RED : ChatFormatting.GREEN)
                                                        .append(Component.translatable(ID + ".fe")
                                                                        .append(fe + "fe/t")));
                }
                if (server.contains("status"))
                        tooltip.add(Component.translatable(ID + "." + server.getString("status")));

        }

        @Override
        public void appendServerData(CompoundTag data, BlockAccessor accessor) {
                ReactorControllerBE be = (ReactorControllerBE) accessor.getBlockEntity();
                data.putInt("fe", be.getFe());
                data.putDouble("heat", be.getHeat());
                data.putString("status", be.getStatus());
                data.putInt("aoe", be.radius());
        }

        @Override
        public ResourceLocation getUid() {
                return x.rl(zStatic.ReactorStuff.controller);
        }

}