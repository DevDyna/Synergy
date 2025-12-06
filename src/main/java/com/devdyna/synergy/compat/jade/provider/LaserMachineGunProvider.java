package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.laser.machine_gun.AbstractLaserMachine;
import com.devdyna.synergy.utils.x;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum LaserMachineGunProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(
      ITooltip tooltip,
      BlockAccessor accessor,
      IPluginConfig config) {

    var server = accessor.getServerData();

    if (server.contains("red") && server.contains("green") && server.contains("blue"))
      tooltip.add(Component.translatable(Main.ID + ".color",
          String.format("0x§c%02X§a%02X§9%02X", server.getInt("red"), server.getInt("green"), server.getInt("blue"))));

  }

  @Override
  public void appendServerData(CompoundTag data, BlockAccessor accessor) {
    AbstractLaserMachine lmg = (AbstractLaserMachine) accessor.getBlockEntity();
    data.putInt("red", lmg.getRed());
    data.putInt("green", lmg.getGreen());
    data.putInt("blue", lmg.getBlue());
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.Lazers.machine_gun);
  }

}