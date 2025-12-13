package com.devdyna.synergy.compat.jade.provider;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.SecondaryMachineResult;
import com.devdyna.synergy.utils.x;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
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

    IElementHelper helper = IElementHelper.get();
    tooltip.add(helper.item(data.inventory.get(0)));
    tooltip.append(helper.spacer(4, 0));
    tooltip.append(helper.progress((float) data.progress / data.total).translate(new Vec2(-2, 0)));
    tooltip.append(helper.item(data.inventory.get(1)));
    if (data.hasSecondary)
      tooltip.append(helper.item(data.inventory.get(2)));
  }

  @Override
  public Data streamData(BlockAccessor accessor) {
    BaseMachineBE machineBE = (BaseMachineBE) accessor.getBlockEntity();

    List<ItemStack> slots = new ArrayList<>();
    slots.add(machineBE.getInput());
    slots.add(machineBE.getOutput());

    if (machineBE instanceof SecondaryMachineResult r) {
      slots.add(r.getSecondary());
    }

    return new Data(
        machineBE.getProgress(),
        machineBE.getMaxProgress(),
        slots,
        machineBE instanceof SecondaryMachineResult);
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(int progress, int total, List<ItemStack> inventory, boolean hasSecondary) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_INT, Data::progress,
        ByteBufCodecs.VAR_INT, Data::total,
        ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inventory,
        ByteBufCodecs.BOOL, Data::hasSecondary,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.Machines.TYPE);
  }

}