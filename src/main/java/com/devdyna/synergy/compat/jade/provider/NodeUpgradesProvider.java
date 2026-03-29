package com.devdyna.synergy.compat.jade.provider;

import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.api.utils.x;
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

public enum NodeUpgradesProvider
    implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, NodeUpgradesProvider.Data> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null)
      return;

    IElementHelper helper = IElementHelper.get();

    tooltip.add(helper.spacer(0, 0));

    if (!data.stack.isEmpty())
      tooltip.append(helper.item(data.stack, 0.75f).size(new Vec2(15, 15)));

    if (!data.speed.isEmpty())
      tooltip.append(helper.item(data.speed, 0.75f).size(new Vec2(15, 15)));

    tooltip.add(helper.spacer(0, 0));

    if (!data.string.isEmpty() || data.string != null)
      tooltip.append(helper.text(Component.literal(data.string)));

    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE);
    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE_ITEMS_PER_LINE);

  }

  @Override
  public int getDefaultPriority() {
    return TooltipPosition.TAIL - 2;
  }

  @Override
  public Data streamData(BlockAccessor a) {
    var be = (NodeBaseBE) a.getBlockEntity();

    return new Data(be.getStorage().getStackInSlot(NodeBaseBE.SPEED_UPGRADE_SLOT),
        be.getStorage().getStackInSlot(NodeBaseBE.STACK_UPGRADE_SLOT),
        be.getStack() +" "+ be.getSuffix() + " every " + (be.getSpeed() < 1 ? "tick" : be.getSpeed() + " ticks"));
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(ItemStack speed, ItemStack stack, String string) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ItemStack.OPTIONAL_STREAM_CODEC, Data::speed,
        ItemStack.OPTIONAL_STREAM_CODEC, Data::stack,
        ByteBufCodecs.STRING_UTF8, Data::string,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl("node_upgrades");
  }

}