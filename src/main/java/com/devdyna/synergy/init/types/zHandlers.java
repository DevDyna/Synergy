package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import java.util.function.Supplier;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.beLogic.*;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class zHandlers {
    public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
            Keys.ATTACHMENT_TYPES,
            ID);

    // ---------------------------------------------------------------------------------------//

    public static final Supplier<AttachmentType<EnergyStorage>> ENERGY_STORAGE = zHandler.register(
            "energy_storage",
            () -> AttachmentType.serializable(h -> (h instanceof EnergyBlock be)
                    ? new EnergyStorage(be.MaxFE())
                    : null).build());

    public static final Supplier<AttachmentType<ItemStackHandler>> ITEM_STORAGE = zHandler.register(
            "item_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof ItemStorageBlock be)
                    return new ItemStackHandler(be.MachineSlots());
                return null;
            }).build());

    public static final Supplier<AttachmentType<FluidStorageTank>> FLUID_TANK = zHandler.register(
            "fluid_tank", () -> AttachmentType.serializable(h -> {
                if (h instanceof BlockEntity be && h instanceof SimpleFluidStorage t)
                    return new FluidStorageTank(be,t.getFluidCapacity());
                return null;
            }).build());


    public static final Supplier<AttachmentType<ItemStackHandler>> MACHINE_HANDLER = zHandler.register(
            "items", () -> AttachmentType.serializable(h -> {
                if (h instanceof MachineItemAutomation be)
                    return new ItemStackHandler(be.getMachineSlots());
                return null;
            }).build());

}
