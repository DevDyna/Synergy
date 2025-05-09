package com.devdyna.modname_id.init.types;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class zProperties {
        public static void register(IEventBus bus) {
    }
    // ---------------------------------------------------------------------------------------//

    public static final BlockBehaviour.Properties bProp = BlockBehaviour.Properties.of();
    public static final Properties iProp = new Item.Properties();
    public static final Properties iPropBucket = iProp.craftRemainder(Items.BUCKET).stacksTo(1);

    // public final static BaseFlowingFluid.Properties FProp = new BaseFlowingFluid.Properties(
    //         zFluidTypes.CRYSTALLINE_FLUID_TYPE,
    //         zFluids.CRYSTALLINE_SOURCE,
    //         zFluids.CRYSTALLINE_FLOWING)
    //         .bucket(zItems.CRYSTALLINE_BUCKET)
    //         .block(zBlocks.CRYSTALLINE_FLUID);

}
