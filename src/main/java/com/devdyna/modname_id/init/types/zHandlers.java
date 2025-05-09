package com.devdyna.modname_id.init.types;

import static com.devdyna.modname_id.Main.MODID;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class zHandlers {
        public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(Keys.ATTACHMENT_TYPES,
            MODID);

    // ---------------------------------------------------------------------------------------//

    // public static final Supplier<AttachmentType<JustDireFluidTank>> THERMO_FUELS = zHandler.register("thermo_fuels",
    //         () -> AttachmentType.serializable((h) -> (h instanceof FluidMachineBE f)
    //                 ? new JustDireFluidTank(f.getMaxMB(), (fs) -> fs.is(zFluidTags.THERMO_COOLERS))
    //                 : new JustDireFluidTank(0)).build());
}
