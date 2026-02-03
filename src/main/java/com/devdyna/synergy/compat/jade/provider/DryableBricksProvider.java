package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.PlaceableBrickBlock;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public enum DryableBricksProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

    var nbt = a.getServerData();

    var dried = nbt.getBoolean("dried");
    var wet = nbt.getBoolean("wet");
    var stage = nbt.getInt("stage");

    if (dried) {
      t.add(Component.translatable(ID + ".jade.bricks.dried"));
    } else {
      if (wet)
        t.add(Component.translatable(ID + ".jade.bricks.wet"));
      t.add(Component.translatable(ID + ".jade.bricks.stage",
          (wet ? "§c" : "§a") + ((float) (stage + 1) * 15) + "%"));
    }

  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.DryableBricks.TYPE);
  }

  @Override
  public void appendServerData(CompoundTag c, BlockAccessor a) {
    var state = a.getBlockState();

    c.putBoolean("dried", state.getValue(PlaceableBrickBlock.DRIED));
    c.putBoolean("wet", state.getValue(PlaceableBrickBlock.WET));
    c.putInt("stage", state.getValue(PlaceableBrickBlock.DRY_STAGE));

  }

}