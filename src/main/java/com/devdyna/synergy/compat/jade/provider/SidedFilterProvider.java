package com.devdyna.synergy.compat.jade.provider;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.automation.router.RouterBE;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum SidedFilterProvider
    implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, SidedFilterProvider.Data> {
  INSTANCE;

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {

    Data data = decodeFromData(accessor).orElse(null);
    if (data == null)
      return;

    IElementHelper helper = IElementHelper.get();

    if (!data.filters.isEmpty()) {

      tooltip.add(helper
          .text(
              Component.literal(getColor(data.dir) + "§l" + StringUtil.nameCapitalized(data.dir.getName()) + ": §f")));

      int empty = 0;

      for (ItemStack f : data.filters) {

        if (f.isEmpty()) {
          empty++;
          continue;
        }

        tooltip.append(helper.item(f, 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1.5f)));
      }

      if (empty >= 3)
        tooltip.append(helper
            .text(Component.literal("Empty")));

    }

    if (!data.internal.isEmpty())
      tooltip.add(helper.item(data.internal, 0.75f).size(new Vec2(15, 15)).translate(new Vec2(0f, -0.5f)));

    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE);
    tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE_ITEMS_PER_LINE);

  }

  @Override
  public int getDefaultPriority() {
    return TooltipPosition.TAIL - 2;
  }

  @Override
  public Data streamData(BlockAccessor a) {
    var be = (RouterBE) a.getBlockEntity();
    var dir = a.getPlayer().isCrouching() ? a.getSide().getOpposite() : a.getSide();
    return new Data(be.getInternal(), be.getItemFiltersBySide(dir), dir);
  }

  private String getColor(Direction dir) {
    return List.of("§d", "§6", "§b", "§c", "§e", "§a").get(Arrays.asList(Direction.values()).indexOf(dir));
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
    return Data.STREAM_CODEC;
  }

  public record Data(ItemStack internal, List<ItemStack> filters, Direction dir) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
        ItemStack.OPTIONAL_STREAM_CODEC, Data::internal,
        ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::filters,
        Direction.STREAM_CODEC, Data::dir,
        Data::new);
  }

  @Override
  public ResourceLocation getUid() {
    return x.rl(zStatic.Blocks.router);
  }

}