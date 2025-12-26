package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import java.util.function.Supplier;

import com.devdyna.synergy.api.beLogic.*;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zHandlers {
    public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
            Keys.ATTACHMENT_TYPES,
            ID);

    // ---------------------------------------------------------------------------------------//

    public static final Supplier<AttachmentType<EnergyHandler>> ENERGY_STORAGE = zHandler.register(
            "energy_storage",
            () -> AttachmentType.serializable(h -> (h instanceof EnergyBlock be)
                    ? new EnergyHandler((be).MaxFE())
                    : null).build());

    public static final Supplier<AttachmentType<ItemStacksResourceHandler>> ITEM_STORAGE = zHandler.register(
            "item_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof ItemStorageBlock be)
                    return new ItemStacksResourceHandler(be.MachineSlots());
                return new ItemStacksResourceHandler(1);
            }).build());


public static final Supplier<AttachmentType<ItemStacksResourceHandler>> MACHINE_HANDLER = zHandler.register(
            "items", () -> AttachmentType.serializable(h -> {
                if (h instanceof MachineItemAutomation be)
                    return new ItemStacksResourceHandler(be.getMachineSlots());
                return new ItemStacksResourceHandler(1);
            }).build());

              

}
