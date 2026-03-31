package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.beLogic.FoundryFuelProvider;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum FoundryHeaterProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();
    if (!nbt.contains("foundry_fuel"))
      return;

    if (nbt.getFloat("foundry_fuel") > 0)
      t.add(Component
          .translatable(ID + ".jade.foundry_fuel.tip", (nbt.getFloat("foundry_fuel") >= 1.0f ? "§a" : "§c")
              + StringUtil.cut(nbt.getFloat("foundry_fuel")) + "x"));

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("foundry_fuel");
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var be = a.getBlockEntity();

    if (be instanceof FoundryFuelProvider m)
      c.putFloat("foundry_fuel", m.getSpeedModifier());

  }

}