package com.devdyna.synergy.init.types;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;

/**
 * @deprecated
 */
@Deprecated
public class zProperties {
    public static void register(IEventBus bus) {
    }
    // ---------------------------------------------------------------------------------------//

    /**
     * @deprecated
     */
    @Deprecated
    public static final BlockBehaviour.Properties bProp = BlockBehaviour.Properties.of();
    /**
     * @deprecated
     */
    @Deprecated
    public static final Properties iProp = new Item.Properties();
    /**
     * @deprecated
     */
    @Deprecated
    public static final Properties iPropBucket = iProp.craftRemainder(Items.BUCKET).stacksTo(1);

}
