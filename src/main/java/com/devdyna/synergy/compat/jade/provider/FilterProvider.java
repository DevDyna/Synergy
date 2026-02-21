package com.devdyna.synergy.compat.jade.provider;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.magic.logic_box.LogicBoxBE;

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

public enum FilterProvider
    implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, FilterProvider.Data> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null)
      return;

    IElementHelper helper = IElementHelper.get();

    if (!data.filter.isEmpty()) {
      tooltip.add(helper.text(Component.translatable(ID + ".jade.info."+(data.inverse?"blacklist":"whitelist"))));
      tooltip.append(helper.item(data.filter, 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1.5f)));
    }

    if (!data.slot.isEmpty())
      tooltip.add(helper.item(data.slot, 0.75f).size(new Vec2(15, 15)).translate(new Vec2(0f, -0.5f)));

    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE);
    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE_ITEMS_PER_LINE);

  }

  @Override
  public int getDefaultPriority() {
    return TooltipPosition.TAIL - 2;
  }

  @Override
  public Data streamData(BlockAccessor a) {
    var be = (LogicBoxBE) a.getBlockEntity();

    return new Data(be.getFilterSlot(), be.getStorageSlot(),be.isInverted());
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(ItemStack filter, ItemStack slot,Boolean inverse) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ItemStack.OPTIONAL_STREAM_CODEC, Data::filter,
        ItemStack.OPTIONAL_STREAM_CODEC, Data::slot,
        ByteBufCodecs.BOOL,Data::inverse,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.Blocks.logic_box);
  }

}