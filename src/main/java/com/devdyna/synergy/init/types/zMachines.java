package com.devdyna.synergy.init.types;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterBE;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterBlock;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.AlloySmelterMenu;
import com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe.AlloySmelterRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorBE;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorBlock;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.CompressorMenu;
import com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe.CompressorRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorBE;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorBlock;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.ExtractorMenu;
import com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe.ExtractorRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceBE;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceBlock;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.ElectricFurnaceMenu;
import com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe.ElectricFurnaceRecipeType;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorBE;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorBlock;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.MaceratorMenu;
import com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe.MaceratorRecipeType;

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
                zStatic.Machines.macerator,
                MaceratorBlock::new,
                MaceratorBE::new,
                MaceratorMenu::new,
                MaceratorRecipeType.Serializer::new
        );

    public static final MachineType<
        AlloySmelterBlock,
        AlloySmelterBE,
        AlloySmelterMenu,
        AlloySmelterRecipeType
        > ALLOY_SMELTER = new MachineType<>(
                zStatic.Machines.alloy_smelter,
                AlloySmelterBlock::new,
                AlloySmelterBE::new,
                AlloySmelterMenu::new,
                AlloySmelterRecipeType.Serializer::new
        );
    public static final MachineType<
        CompressorBlock,
        CompressorBE,
        CompressorMenu,
        CompressorRecipeType
        > COMPRESSOR = new MachineType<>(
                zStatic.Machines.compressor,
                CompressorBlock::new,
                CompressorBE::new,
                CompressorMenu::new,
                CompressorRecipeType.Serializer::new
        );

    public static final MachineType<
        ElectricFurnaceBlock,
        ElectricFurnaceBE,
        ElectricFurnaceMenu,
        ElectricFurnaceRecipeType
        > ELECTRIC_FURNACE = new MachineType<>(
                zStatic.Machines.electric_furnace,
                ElectricFurnaceBlock::new,
                ElectricFurnaceBE::new,
                ElectricFurnaceMenu::new,
                ElectricFurnaceRecipeType.Serializer::new
        );

    public static final MachineType<
        ExtractorBlock,
        ExtractorBE,
        ExtractorMenu,
        ExtractorRecipeType
        > EXTRACTOR = new MachineType<>(
                zStatic.Machines.extractor,
                ExtractorBlock::new,
                ExtractorBE::new,
                ExtractorMenu::new,
                ExtractorRecipeType.Serializer::new
        );


}
