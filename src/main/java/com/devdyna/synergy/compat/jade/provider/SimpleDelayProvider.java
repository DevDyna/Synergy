package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.api.beLogic.SimpleTickerDelay;
import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.utils.TimeUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum SimpleDelayProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();
    if (!nbt.contains("diff"))
      return;

    t.add(Component.literal(TimeUtil.getTimeValue(nbt.getInt("diff"), 0, false)+ " left"));

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("simple_timer");
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var be = a.getBlockEntity();

    if (be instanceof SimpleTickerDelay m && m.getTicker() != null && !(be instanceof TimeredRecipe)) {
      c.putInt("diff", m.getTicker().diff());
    }

  }

}