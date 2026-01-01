package com.devdyna.synergy.init.builder;

import java.util.function.Consumer;

import com.devdyna.synergy.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("deprecation")
public class BlockTipped extends BlockItem {

    private String traslationkey;

    public BlockTipped(Block block, Properties properties, String traslationkey) {
        super(block, properties);
        this.traslationkey = traslationkey;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
            Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(Main.ID + "." + traslationkey));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

}
