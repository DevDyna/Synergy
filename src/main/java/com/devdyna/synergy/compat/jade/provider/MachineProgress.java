package com.devdyna.synergy.compat.jade.provider;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.machines.BaseMachineBE;
import com.devdyna.synergy.api.machines.ExtraMachineSlot;
import com.devdyna.synergy.api.machines.ExtraMachineSlot.TYPE;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum MachineProgress
    implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, MachineProgress.Data> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    // TODO remove default item capability tooltip

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null)
      return;

    // render only when has content
    if (!isEmpty(data) || data.progress != 0) {
      var be = (BaseMachineBE) accessor.getBlockEntity();

      IElementHelper helper = IElementHelper.get();

      var check = be instanceof ExtraMachineSlot slot && slot.getSlotType().equals(TYPE.OUTPUT);

      tooltip.add(helper.item(data.inv.get(0)));

      tooltip.append(helper.spacer(4, 0));

      if (!check && be instanceof ExtraMachineSlot) {
        tooltip.append(helper.item(data.inv.get(2)));
        tooltip.append(helper.spacer(4, 0));
      }

      tooltip.append(helper.progress((float) data.progress / data.total).translate(new Vec2(-2, 0)));

      tooltip.append(helper.item(data.inv.get(1)));

      if (check)
        tooltip.append(helper.item(data.inv.get(2)));
    }

  }

  private boolean isEmpty(Data data) {
    return data.inv.get(0).isEmpty() && data.inv.get(1).isEmpty()
        && (data.hasSecondary ? data.inv.get(2).isEmpty() : true);
  }

  @Override
  public Data streamData(BlockAccessor accessor) {
    BaseMachineBE machineBE = (BaseMachineBE) accessor.getBlockEntity();

    List<ItemStack> slots = new ArrayList<>();
    slots.add(machineBE.getInput());
    slots.add(machineBE.getOutput());

    if (machineBE instanceof ExtraMachineSlot r) {
      slots.add(r.getExtraSlot());
    }

    return new Data(
        machineBE.getProgress(),
        machineBE.getMaxProgress(),
        slots,
        machineBE instanceof ExtraMachineSlot);
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(int progress, int total, List<ItemStack> inv, boolean hasSecondary) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT, Data::progress,
        ByteBufCodecs.VAR_INT, Data::total,
        ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inv,
        ByteBufCodecs.BOOL, Data::hasSecondary,
        Data::new);
  }

  @Override
  public Identifier getUid() {
    return x.rl(zStatic.Machines.TYPE);
  }

}