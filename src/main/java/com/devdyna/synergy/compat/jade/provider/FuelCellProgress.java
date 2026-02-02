package com.devdyna.synergy.compat.jade.provider;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.TimeUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBE;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum FuelCellProgress
    implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, FuelCellProgress.Data> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    // remove default item capability tooltip

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null || data.inv == null)
      return;

    // render only when has content
    if (!((data.inv.get(0).isEmpty() || data.inv.get(0) == null)
        && (data.inv.get(1).isEmpty() || data.inv.get(1) == null)) || data.progress != 0) {

      tooltip.add(Component.literal(TimeUtil.getTimeValue(data.total - data.progress,false) + " left"));

      IElementHelper helper = IElementHelper.get();

      tooltip.add(helper.item(data.inv.get(0)));

      tooltip.append(helper.spacer(4, 0));

      tooltip.append(helper.progress((float) data.progress / data.total).translate(new Vec2(-2, 0)));

      tooltip.append(helper.item(data.inv.get(1)));

    }

  }

  @Override
  public Data streamData(BlockAccessor accessor) {
    FuelCellBE fuelCellBE = (FuelCellBE) accessor.getBlockEntity();

    List<ItemStack> slots = new ArrayList<>();

    try {
      slots.add(fuelCellBE.getInputStack());
      slots.add(fuelCellBE.getRecipe().get().value().getOutput());
    } catch (Exception e) {
      slots = List.of(ItemStack.EMPTY, ItemStack.EMPTY);
    }

    return new Data(
        fuelCellBE.getProgress(),
        fuelCellBE.getMaxProgress(),
        slots);
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(int progress, int total, List<ItemStack> inv) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT, Data::progress,
        ByteBufCodecs.VAR_INT, Data::total,
        ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inv,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.ReactorStuff.fuel_cell);
  }

}