package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.laser.dynamo.LaserRotorBE;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum LaserRotorProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();

        // possible broken compoundtag

        if (server.contains("laserData")) {
            var laserData = server.getCompound("laserData");

            if (laserData.contains("blockpos")) {
                var blockpos = laserData.getIntArray("blockpos");
                tooltip.add(
                        Component.translatable(Main.ID + ".blockpos")
                                .append(Component.literal((blockpos == null) ? "Empty"
                                        : ("[ " + blockpos[0] + " | " + blockpos[1]
                                                + " | " + blockpos[2] + " ]")))

                );
            }
            if (laserData.contains("dir")) {
                var dirs = laserData.getCompound("dir");
                tooltip.add(
                        Component.translatable(Main.ID + ".dirs")
                                .append(
                                        Component.literal(" ["))
                                .append(
                                        Component.literal("north")
                                                .withStyle(dirs.getBoolean("north") ? ChatFormatting.GREEN
                                                        : ChatFormatting.RED))
                                .append(
                                        Component.literal(" | ")
                                                .withStyle(ChatFormatting.GRAY))
                                .append(
                                        Component.literal("south")
                                                .withStyle(dirs.getBoolean("south") ? ChatFormatting.GREEN
                                                        : ChatFormatting.RED))
                                .append(
                                        Component.literal(" | ")
                                                .withStyle(ChatFormatting.GRAY))
                                .append(
                                        Component.literal("east")
                                                .withStyle(dirs.getBoolean("east") ? ChatFormatting.GREEN
                                                        : ChatFormatting.RED))
                                .append(
                                        Component.literal(" | ")
                                                .withStyle(ChatFormatting.GRAY))
                                .append(
                                        Component.literal("west")
                                                .withStyle(dirs.getBoolean("west") ? ChatFormatting.GREEN
                                                        : ChatFormatting.RED))
                                .append(
                                        Component.literal(" ]")));

            }
        }

    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        LaserRotorBE lr = (LaserRotorBE) accessor.getBlockEntity();
        data.put("laserData", lr.getData());
    }

    @Override
    public ResourceLocation getUid() {
        return x.rl(zStatic.Lazers.electromagnetic_rotor);
    }

}