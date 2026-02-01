package com.devdyna.synergy;

import java.util.stream.Stream;

import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zCreativeTab;
import com.devdyna.synergy.init.types.zItems;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CreativeTabs {

    public static void register(BuildCreativeModeTabContentsEvent event) {

        // ---------------------------------------------------------- //
        // MACHINES
        if (event.getTabKey() == zCreativeTab.MACHINES.getKey()) {

            ClazzUtil.getAllMachineTypes()
                    .forEach(i -> event.accept((Item) i.item().get()));

            event.accept(zStatic.MachineUpgrades.TemplateUpgrades.ENERGY);
            event.accept(zStatic.MachineUpgrades.TemplateUpgrades.SPEED);
            event.accept(zStatic.MachineUpgrades.TemplateUpgrades.LUCK);
            event.accept(zStatic.MachineUpgrades.TemplateUpgrades.FLUID);
        }

        // ---------------------------------------------------------- //
        // LASER STUFF
        if (event.getTabKey() == zCreativeTab.LASER_STUFF.getKey()) {
            event.accept(zBlocks.LASER_MACHINE.get());
            event.accept(zBlocks.LASER_LENS.get());
            event.accept(zBlocks.LASER_MIRROR.get());
            event.accept(zBlocks.LASER_ROTOR.get());
            event.accept(zBlocks.LASER_SENSOR.get());
        }

        // ---------------------------------------------------------- //
        // NUCLEAR STUFF
        if (event.getTabKey() == zCreativeTab.NUCLEAR_STUFF.getKey()) {

            ClazzUtil.getAllzBlocks(
                    zBlocks.zModerators,
                    zBlocks.zCoolers)
                    .forEach(i -> event.accept(i.get()));
            event.accept(zBlocks.REACTOR_CONTROLLER.get());
            event.accept(zBlocks.REACTOR_FUEL_CELL.get());
        }

        // ---------------------------------------------------------- //
        // TOOLS
        if (event.getTabKey() == zCreativeTab.TOOLS.getKey()) {

            ClazzUtil.getAllzItems(zItems.zTool)
                    .forEach(item -> event.accept(item.get()));
        }

        // ---------------------------------------------------------- //
        // AGRICULTURE
        if (event.getTabKey() == zCreativeTab.AGRICULTURE.getKey()) {

            event.accept(zBlocks.AZALEA.get());
            event.accept(zBlocks.WILD_CAVE_WHEAT.get());
            event.accept(zBlocks.WILD_COTTON.get());
            event.accept(zBlocks.WILD_RICE.get());

            ClazzUtil.getAllzItems(
                    zItems.zSeeds,
                    zItems.zCropExtra,
                    zItems.zFoods)
                    .forEach(i -> event.accept(i.get()));
        }

        // ---------------------------------------------------------- //
        // DECORATIVE
        if (event.getTabKey() == zCreativeTab.DECORATIVE_BLOCKS.getKey()) {

            ClazzUtil.getAllzBlocks(
                    zBlocks.zDecorative,
                    zBlocks.zBlockStair,
                    zBlocks.zBlockSlab,
                    zBlocks.zMachineFrame,
                    zBlocks.zColumn)
                    .forEach(i -> event.accept(i.get()));

            zBlocks.FIRECLAY_BRICKS.buildCreativeTab(() -> event);
            zBlocks.FIRECLAY_BRICK_CRACKED.buildCreativeTab(() -> event);
            zBlocks.FIRECLAY_BRICK_MOSSY.buildCreativeTab(() -> event);

        }

        // ---------------------------------------------------------- //
        // AUTOMATION
        if (event.getTabKey() == zCreativeTab.AUTOMATION.getKey()) {
            event.accept(zBlocks.SOLAR_PANEL.get());
            event.accept(zBlocks.HARVESTER.get());
            event.accept(zBlocks.SPRINKLER.get());
            event.accept(zBlocks.SIMPLE_TANK.get());
            ClazzUtil.getAllzBlocks(zBlocks.zResourceGenerators)
                    .forEach(i -> event.accept(i.get()));
        }

        // ---------------------------------------------------------- //
        // LOGISTICS
        if (event.getTabKey() == zCreativeTab.LOGISTICS.getKey()) {
            event.accept(zBlocks.PIPE.get());
            event.accept(zBlocks.ITEM_PROVIDER.get());
            event.accept(zBlocks.ITEM_RETRIEVAL.get());
            event.accept(zBlocks.ITEM_TRANSFER.get());
            event.accept(zBlocks.FLUID_PROVIDER.get());
            event.accept(zBlocks.FLUID_RETRIEVAL.get());
            event.accept(zBlocks.FLUID_TRANSFER.get());
            event.accept(zBlocks.ENERGY_RETRIEVAL.get());
            event.accept(zBlocks.ENERGY_TRANSFER.get());
        }

        // ---------------------------------------------------------- //
        // MAGIC
        if (event.getTabKey() == zCreativeTab.MAGIC.getKey()) {
            event.accept(zBlocks.QUERN.get());
            event.accept(zBlocks.URN.get());
            event.accept(zBlocks.HEALER.get());
            event.accept(zBlocks.WOODEN_TINY_CHEST.get());
            event.accept(zBlocks.STONE_TINY_CHEST.get());
            event.accept(zBlocks.ORNATE_TINY_CHEST.get());
            event.accept(zBlocks.VOID_BOX.get());
        }
        // ---------------------------------------------------------- //
        // SURVIVAL
        if (event.getTabKey() == zCreativeTab.SURVIVAL.getKey()) {
            event.accept(zBlocks.SIMPLE_MELTER.get());
            event.accept(zBlocks.FUEL_TANK.get());
            event.accept(zBlocks.CRUSHING_TUB.get());
            event.accept(zBlocks.EVAPORATION_BASIN.get());
            zStatic.ALL_DRYING_RACKS.stream().map(DeferredHolder::get).forEach(event::accept);
        }

        // ---------------------------------------------------------- //
        // REDSTONE
        if (event.getTabKey() == zCreativeTab.REDSTONE.getKey()) {
            event.accept(zBlocks.PULSE_REPEATER.get());
            event.accept(zBlocks.RECURSIVE_REPEATER.get());
            event.accept(zBlocks.INVERTED_REPEATER.get());
        }
        // ---------------------------------------------------------- //
        // RESOURCES
        if (event.getTabKey() == zCreativeTab.RESOURCES.getKey()) {

            Stream.of(
                    zItems.zCraftingComponents,
                    zItems.zDropLets,
                    zItems.zResources,
                    zItems.zDusts,
                    zItems.zFoils,
                    zItems.zGems,
                    zItems.zIngots,
                    zItems.zNuggets,
                    zItems.zPlates,
                    zItems.zElectronTubes,
                    zItems.zRawOres,
                    zItems.zShards,
                    zItems.zMobDrop,
                    zItems.zPellets,
                    zItems.zCoils,
                    zItems.zGears,
                    zItems.zMolds,
                    zItems.zBucketItems).forEach(r -> r.getEntries().forEach(i -> event.accept(i.get())));

        }
    }

}