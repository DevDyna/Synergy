package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.TimeUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum TimeredRecipeProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();
    if (!nbt.contains("status") || !nbt.contains("max") || !nbt.contains("multiplier"))
      return;

    if ((nbt.getInt("max") - nbt.getInt("status")) > 0)
      t.add(Component
          .literal("[" + (nbt.getFloat("multiplier") >= 1.0f ? "§a" : "§c")
              + StringUtil.cut(nbt.getFloat("multiplier")) + "x§7] "
              + TimeUtil.getTimeValue(nbt.getInt("max") - nbt.getInt("status"), 0, false) + " left"));

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("timered_recipes");
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var be = a.getBlockEntity();
    if (be instanceof TimeredRecipe t) {
      var ticker = t.getTicker();

      if (ticker != null) {
        c.putFloat("multiplier", t.getTickerSpeed());
        c.putInt("status", ticker.get());
        c.putInt("max", ticker.max());
      }
    }

  }

}