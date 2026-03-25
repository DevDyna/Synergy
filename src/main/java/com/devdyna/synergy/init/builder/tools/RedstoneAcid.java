package com.devdyna.synergy.init.builder.tools;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.blockfactories.CopperReagentItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;

public class RedstoneAcid extends CopperReagentItem {

    public RedstoneAcid(Properties p) {
        super(p);
    }

    public RedstoneAcid() {
        super(new Item.Properties());
    }

    @Override
    public Block getNextBlock(Block b) {
       return DataMapHooks.getNextOxidizedStage(b);
    }

    @Override
    public Boolean getConfig() {
        return Common.DISABLE_REDSTONE_ACID_EVENT.get();
    }

}
