package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.macerator.MaceratorBE;
import com.devdyna.synergy.api.machine.macerator.MaceratorBlock;
import com.devdyna.synergy.api.machine.macerator.MaceratorMenu;
import com.devdyna.synergy.api.machine.macerator.recipe.MaceratorRecipeType;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zMachines {
    public static void register(IEventBus bus) {
        xBE_MACHINE.register(bus);
        xBLOCKS_MACHINE.register(bus);
        xITEM_MACHINE.register(bus);
        xMENU_MACHINE.register(bus);
        xRCP_SERIAL_MACHINE.register(bus);
        xRCP_TYPE_MACHINE.register(bus);
    }

    public static final DeferredRegister.Blocks xBLOCKS_MACHINE = DeferredRegister.createBlocks(Main.ID);
    public static final DeferredRegister<BlockEntityType<?>> xBE_MACHINE = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.ID);
    public static final DeferredRegister.Items xITEM_MACHINE = DeferredRegister.createItems(Main.ID);
    public static final DeferredRegister<MenuType<?>> xMENU_MACHINE = DeferredRegister.create(Registries.MENU, ID);
    public static final DeferredRegister<RecipeSerializer<?>> xRCP_SERIAL_MACHINE = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, ID);
    public static final DeferredRegister<RecipeType<?>> xRCP_TYPE_MACHINE = DeferredRegister
            .create(Registries.RECIPE_TYPE, ID);

    public static final MachineType<
        MaceratorBlock,
        MaceratorBE,
        MaceratorMenu,
        MaceratorRecipeType
        > MACERATOR = new MachineType<>(
                zStatic.Blocks.macerator,
                MaceratorBlock::new,
                MaceratorBE::new,
                MaceratorMenu::new,
                MaceratorRecipeType.Serializer::new
        );


}
