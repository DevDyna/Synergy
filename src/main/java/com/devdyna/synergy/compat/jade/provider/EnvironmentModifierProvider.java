package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.api.beLogic.EnvironmentModifier;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum EnvironmentModifierProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();
    if (!nbt.contains("speed_modifier"))
      return;

      t.add(Component
          .translatable( ID+".jade.environment_modifier.tip" ,(nbt.getFloat("speed_modifier") >= 1.0f ? "§a" : "§c")
              + StringUtil.cut(nbt.getFloat("speed_modifier")) + "x"));

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("environment_modifier");
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var be = a.getBlockEntity();

    if (be instanceof EnvironmentModifier m)
      c.putFloat("speed_modifier", m.getSpeedModifier());

  }

}