package com.devdyna.synergy.compat.jade.provider;

import java.util.ArrayList;
import java.util.List;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.ExtraMachineSlots;
import com.devdyna.synergy.api.machine.ExtraMachineSlots.SlotType;
import com.devdyna.synergy.api.utils.x;

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

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null || data.inv == null)
      return;

    var be = (BaseMachineBE) accessor.getBlockEntity();

    // render only when has content
    if (!data.inv.isEmpty() && data.progress != 0) {

      IElementHelper helper = IElementHelper.get();

      // calculateTanks(be, data, helper, tooltip, FluidTankType.INPUT);

      if (data.inv.size() > 0) {
        tooltip.add(helper.item(data.inv.get(0)));

        tooltip.append(helper.spacer(4, 0));
      }

      calculateSlots(be, data, helper, tooltip, SlotType.INPUT);

      // arrow
      tooltip.append(helper.progress((float) data.progress / data.total).translate(new Vec2(-2, 0)));

      if (data.inv.size() > 1) {
        tooltip.append(helper.item(data.inv.get(1)));
        tooltip.append(helper.spacer(4, 0));
      }

      // calculateTanks(be, data, helper, tooltip, FluidTankType.OUTPUT);

      calculateSlots(be, data, helper, tooltip, SlotType.OUTPUT);

      // tooltip.remove(JadeIds.CORE_MOD_NAME);
      tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE);
      tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE_ITEMS_PER_LINE);
    }

  }

  @Override
  public int getDefaultPriority() {
    return TooltipPosition.TAIL-2;
  }

  protected void calculateSlots(BaseMachineBE be, Data data, IElementHelper helper, ITooltip tooltip, SlotType type) {
    if (be instanceof ExtraMachineSlots extra) {
      if (data.inv().size() > 2) {
        for (int i = 2; i < data.inv.size() - 2; i++) {
          if (!data.inv.get(i).isEmpty() && extra.getSlotTypes().get().get(i - 2).equals(type)) {
            tooltip.append(helper.item(data.inv.get(i)));
            tooltip.append(helper.spacer(4, 0));
          }
        }
      }
    }
  }

  // protected void calculateTanks(BaseMachineBE be, Data data, IElementHelper
  // helper, ITooltip tooltip,
  // FluidTankType type) {
  // if (be instanceof FluidTankStorage tank)
  // if (data.fluidTank != null && !data.fluidTank.isEmpty())
  // if (tank.getTankIOType().equals(type)) {
  // tooltip.add(helper.fluid(JadeFluidObject.of(data.fluidTank.getFluid(),data.fluidTank.getAmount())));
  // tooltip.append(helper.spacer(4, 0));
  // }
  // }

  @Override
  public Data streamData(BlockAccessor accessor) {
    BaseMachineBE machineBE = (BaseMachineBE) accessor.getBlockEntity();

    List<ItemStack> slots = new ArrayList<>();
    // FluidStack fluidTank = FluidStack.EMPTY;
    if (machineBE.getMachineSlots() > 4)
      slots.add(machineBE.getInput());
    if (machineBE.getMachineSlots() > 5)
      slots.add(machineBE.getOutput());

    if (machineBE instanceof ExtraMachineSlots && machineBE.getMachineSlots() > 6) {
      for (int i = 6; i < machineBE.getMachineSlots(); i++) {
        slots.add(machineBE.getStorage().getStackInSlot(i));
      }
    }

    // if (machineBE instanceof FluidTankStorage tank) {
    // fluidTank = tank.getFluidStorage().getFluid();
    // }

    return new Data(
        machineBE.getProgress(),
        machineBE.getMaxProgress(),
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
        // FluidStack.OPTIONAL_STREAM_CODEC,Data::fluidTank,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.Machines.TYPE);
  }
}
