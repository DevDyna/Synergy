package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBE;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum HeatInfoProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();
    if (!nbt.contains("heat") || !nbt.contains("max"))
      return;

    t.add(Component.literal("Heat: " + nbt.getInt("heat") + "°C [Max: " + nbt.getInt("max") + "°C]"));

    if (!nbt.contains("decay"))
      return;

    if (nbt.getBoolean("decay"))
      t.add(Component.literal("§cMissing fuel!"));

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("heating");
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var be = a.getBlockEntity();

    if (be instanceof SolidFuelHeaterBE m) {
      c.putInt("heat", m.getHeat());
      c.putInt("max", m.getHeatCap());
      c.putBoolean("decay", m.isDecay());
    }

  }

}