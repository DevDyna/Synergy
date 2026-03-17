package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.DataGenUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings({ "null", "unused" })
public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput output, CompletableFuture<Provider> registries,
                        ExistingFileHelper existingFileHelper) {
                super(output, registries, existingFileHelper, List.of(new DataAdvancementGenerator()));
        }

        public static class DataAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> h, ExistingFileHelper e) {

                        var root_wrought_iron = Advancement.Builder.advancement()
                                        .display(zItems.WROUGHT_IRON_INGOT.get(),
                                                        Component.translatable(ID + ".advancement.root.wrought_iron"),
                                                        Component.translatable(
                                                                        ID + ".advancement.root.wrought_iron.desc"),
                                                        x.rl("textures/block/decorative/wrought_iron_block.png"),
                                                        AdvancementType.TASK, false, false, false)
                                        .addCriterion("wrong_way_to_coke_iron",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.WROUGHT_IRON_INGOT.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("wrong_way_to_coke_iron")))
                                        .save(h, ID + ":main/wrought_iron/root");

                        var root_magic = Advancement.Builder.advancement()
                                        .display(zItems.GHOUL_HEART.get(),
                                                        Component.translatable(ID + ".advancement.root.magic"),
                                                        Component.translatable(
                                                                        ID + ".advancement.root.magic.desc"),
                                                        x.rl("textures/block/decorative/waxed_planks.png"),
                                                        AdvancementType.TASK, false, false, false)
                                        .addCriterion("magic",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(new ItemLike[] {}))
                                        .save(h, ID + ":main/magic/root");

                        var root_steel = Advancement.Builder.advancement()
                                        .display(zItems.STEEL_INGOT.get(),
                                                        Component.translatable(ID + ".advancement.root.steel"),
                                                        Component.translatable(ID + ".advancement.root.steel.desc"),
                                                        x.rl("textures/block/decorative/steel_block.png"),
                                                        AdvancementType.TASK, false, false, false)
                                        .addCriterion("correct_way_to_coke_iron",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.STEEL_INGOT.get()))
                                        .requirements(AdvancementRequirements
                                                        .allOf(List.of("correct_way_to_coke_iron")))
                                        .save(h, ID + ":main/steel/root");

                        var wrought_iron = DataGenUtil
                                        .getExistingParent("minecraft:story/smelt_iron",
                                                        zItems.WROUGHT_IRON_INGOT.get(),
                                                        zStatic.ResourceMaterial.wrought_iron, AdvancementType.TASK,
                                                        true, true, false)
                                        .addCriterion("craft_wrought_iron",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.WROUGHT_IRON_INGOT.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_wrought_iron")))
                                        .save(h, ID + ":extend/story/smelt_iron/wrought_iron");

                        var steel = DataGenUtil
                                        .getExistingParent(wrought_iron, zItems.STEEL_INGOT.get(),
                                                        zStatic.ResourceMaterial.steel, AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("craft_steel",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.STEEL_INGOT.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_steel")))
                                        .save(h, ID + ":extend/story/smelt_iron/steel");

                        var ironwood = DataGenUtil
                                        .getExistingParent("minecraft:husbandry/root",
                                                        zItems.IRONBERRIES.get(),
                                                        "ironberries", AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("obtain_ironwood",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.IRON_WOOD.getSapling().get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("obtain_ironwood")))
                                        .save(h, ID + ":extend/husbandry/root/ironwood");

                        var crook = DataGenUtil
                                        .getExistingParent("minecraft:husbandry/root", zItems.WOODEN_CROOK.get(),
                                                        zStatic.Items.wooden_crook, AdvancementType.TASK, true, true,
                                                        false)
                                        .addCriterion("craft_crook",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.WOODEN_CROOK.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_crook")))
                                        .save(h, ID + ":extend/husbandry/root/crook");

                        var mushrooms = DataGenUtil
                                        .getExistingParent("minecraft:husbandry/root", zItems.BLUE_CUP_SPORE.get(),
                                                        "mushrooms",
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("obtain_mushrooms",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item()
                                                                                        .of(zItemTag.MUSHROOM_SEED)
                                                                                        .build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("obtain_mushrooms")))
                                        .save(h, ID + ":extend/husbandry/root/mushrooms");

                        var cake = DataGenUtil
                                        .getExistingParent("minecraft:husbandry/root", zItems.CAKE_STICK.get(),
                                                        zStatic.Items.cake_stick,
                                                        AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("cake_stick",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.CAKE_STICK.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("cake_stick")))
                                        .rewards(AdvancementRewards.Builder.experience(1000))
                                        .save(h, ID + ":extend/husbandry/root/cake");

                        var quern = DataGenUtil
                                        .getExistingParent("minecraft:story/mine_stone", zBlocks.QUERN.get(),
                                                        zStatic.Blocks.quern,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_quern",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.QUERN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_quern")))
                                        .save(h, ID + ":extend/story/mine_stone/quern");

                        var crush = DataGenUtil
                                        .getExistingParent("minecraft:story/mine_stone", zBlocks.CRUSHING_TUB.get(),
                                                        zStatic.Blocks.crushing_tub, AdvancementType.TASK, true, true,
                                                        false)
                                        .addCriterion("craft_crusher",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.CRUSHING_TUB.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_crusher")))
                                        .save(h, ID + ":extend/story/mine_stone/crusher");

                        var eva = DataGenUtil
                                        .getExistingParent("minecraft:story/mine_stone",
                                                        zBlocks.EVAPORATION_BASIN.get(),
                                                        zStatic.Blocks.evaporation_basin, AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("craft_evap_tub",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.EVAPORATION_BASIN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_evap_tub")))
                                        .save(h, ID + ":extend/story/mine_stone/evap_tub");

                        var drying_racks = DataGenUtil
                                        .getExistingParent("minecraft:story/mine_stone",
                                                        zBlocks.BAMBOO_DRYING_RACK.get(),
                                                        zStatic.Blocks.drying_rack, AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("craft_drying_rack",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item()
                                                                                        .of(zItemTag.DRYING_RACKS)
                                                                                        .build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_drying_rack")))
                                        .save(h, ID + ":extend/story/mine_stone/drying_rack");

                        var aquamarine = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zItems.AQUAMARINE.get(),
                                                        zStatic.ResourceMaterial.aquamarine, AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("aquire_aquamarine",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.AQUAMARINE.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("aquire_aquamarine")))
                                        .save(h, ID + ":extend/story/iron_tools/aquamarine");

                        var pipe = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zBlocks.PIPE.get(),
                                                        zStatic.PipeStuff.pipe,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_pipe",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.PIPE.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_pipe")))
                                        .save(h, ID + ":extend/story/iron_tools/pipe");

                        var sulfur = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zItems.SULFUR_DUST.get(),
                                                        zStatic.ResourceMaterial.sulfur, AdvancementType.TASK, true,
                                                        true, false)
                                        .addCriterion("obtain_sulfur",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.SULFUR_DUST.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("obtain_sulfur")))
                                        .save(h, ID + ":extend/story/iron_tools/sulfur");

                        var chisel = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zItems.CHISEL.get(),
                                                        zStatic.Items.chisel,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_chisel",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.CHISEL.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_chisel")))
                                        .save(h, ID + ":extend/story/iron_tools/chisel");

                        var soldering_gun = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zItems.SOLDERING_GUN.get(),
                                                        zStatic.Items.soldering_gun, AdvancementType.TASK, true, true,
                                                        false)
                                        .addCriterion("craft_soldering_gun",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.SOLDERING_GUN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_soldering_gun")))
                                        .save(h, ID + ":extend/story/iron_tools/soldering_gun");

                        var configurator = DataGenUtil
                                        .getExistingParent("minecraft:story/iron_tools", zItems.CONFIGURATOR.get(),
                                                        zStatic.Items.configurator, AdvancementType.TASK, true, true,
                                                        false)
                                        .addCriterion("craft_configurator",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.CONFIGURATOR.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_configurator")))
                                        .save(h, ID + ":extend/story/iron_tools/configurator");

                        var tree_chopper = DataGenUtil
                                        .getExistingParent(root_wrought_iron, zBlocks.CHOPPER.get(),
                                                        zStatic.Blocks.chopper, AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_chopper",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.CHOPPER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_chopper")))
                                        .save(h, ID + ":main/wrought_iron/chopper");

                        var router = DataGenUtil
                                        .getExistingParent(root_wrought_iron, zBlocks.ROUTER.get(),
                                                        zStatic.Blocks.router, AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_router",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ROUTER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_router")))
                                        .save(h, ID + ":main/wrought_iron/router");

                        var foundry = DataGenUtil
                                        .getExistingParent(root_wrought_iron, zBlocks.FOUNDRY.get(),
                                                        zStatic.Blocks.foundry, AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_foundry",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.FOUNDRY.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_foundry")))
                                        .save(h, ID + ":main/wrought_iron/foundry");

                        var simple_cobble = DataGenUtil
                                        .getExistingParent(root_wrought_iron, zBlocks.SIMPLE_COBBLE_GEN.get(),
                                                        zStatic.ResourceGenerators.CobbleStone.simple,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_cobble_simple",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.SIMPLE_COBBLE_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cobble_simple")))
                                        .save(h, ID + ":main/wrought_iron/cobble_simple");

                        var advanced_cobble = DataGenUtil
                                        .getExistingParent(simple_cobble, zBlocks.ADVANCED_COBBLE_GEN.get(),
                                                        zStatic.ResourceGenerators.CobbleStone.advanced,
                                                        AdvancementType.GOAL, true, true, false)
                                        .addCriterion("craft_cobble_advanced",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ADVANCED_COBBLE_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cobble_advanced")))
                                        .rewards(AdvancementRewards.Builder.experience(20))
                                        .save(h, ID + ":main/wrought_iron/cobble_advanced");

                        var elite_cobble = DataGenUtil
                                        .getExistingParent(advanced_cobble, zBlocks.ELITE_COBBLE_GEN.get(),
                                                        zStatic.ResourceGenerators.CobbleStone.elite,
                                                        AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_cobble_elite",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ELITE_COBBLE_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_cobble_elite")))
                                        .rewards(AdvancementRewards.Builder.experience(200))
                                        .save(h, ID + ":main/wrought_iron/cobble_elite");

                        var simple_water = DataGenUtil
                                        .getExistingParent(root_wrought_iron, zBlocks.SIMPLE_WATER_GEN.get(),
                                                        zStatic.ResourceGenerators.Water.simple, AdvancementType.TASK,
                                                        true, true, false)
                                        .addCriterion("craft_water_simple",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.SIMPLE_WATER_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_water_simple")))
                                        .save(h, ID + ":main/wrought_iron/water_simple");

                        var advanced_water = DataGenUtil
                                        .getExistingParent(simple_water, zBlocks.ADVANCED_WATER_GEN.get(),
                                                        zStatic.ResourceGenerators.Water.advanced, AdvancementType.GOAL,
                                                        true, true, false)
                                        .addCriterion("craft_water_advanced",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ADVANCED_WATER_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_water_advanced")))
                                        .rewards(AdvancementRewards.Builder.experience(20))
                                        .save(h, ID + ":main/wrought_iron/water_advanced");

                        var elite_water = DataGenUtil
                                        .getExistingParent(advanced_water, zBlocks.ELITE_WATER_GEN.get(),
                                                        zStatic.ResourceGenerators.Water.elite,
                                                        AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_water_elite",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ELITE_WATER_GEN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_water_elite")))
                                        .rewards(AdvancementRewards.Builder.experience(200))
                                        .save(h, ID + ":main/wrought_iron/water_elite");

                        var urn = DataGenUtil
                                        .getExistingParent(root_magic, zBlocks.URN.get(),
                                                        zStatic.Blocks.urn,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_urn",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.URN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_urn")))
                                        .save(h, ID + ":main/magic/urn");

                        var void_box = DataGenUtil
                                        .getExistingParent(urn, zBlocks.VOID_BOX.get(),
                                                        zStatic.Blocks.void_box,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_void_box",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.VOID_BOX.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_void_box")))
                                        .save(h, ID + ":main/magic/void_box");

                        var logic_box = DataGenUtil
                                        .getExistingParent(urn, zBlocks.LOGIC_BOX.get(),
                                                        zStatic.Blocks.logic_box,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_logic_box",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.LOGIC_BOX.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_logic_box")))
                                        .save(h, ID + ":main/magic/logic_box");

                        var entity_watcher = DataGenUtil
                                        .getExistingParent(urn, zBlocks.ENTITY_WATCHER.get(),
                                                        zStatic.Blocks.entity_watcher,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_entity_watcher",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ENTITY_WATCHER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_entity_watcher")))
                                        .save(h, ID + ":main/magic/entity_watcher");

                        var batteries = DataGenUtil
                                        .getExistingParent(root_steel, zItems.BLUE_BATTERY.get(),
                                                        "battery",
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_batteries",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item()
                                                                                        .of(zItemTag.BATTERIES)
                                                                                        .build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_batteries")))
                                        .save(h, ID + ":main/steel/batteries");

                        var solarpanel = DataGenUtil
                                        .getExistingParent(batteries, zBlocks.SOLAR_PANEL.get(),
                                                        zStatic.Blocks.solar_panel,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_solar_panel",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.SOLAR_PANEL.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_solar_panel")))
                                        .save(h, ID + ":main/steel/solar_panel");

                        var sprinkler = DataGenUtil
                                        .getExistingParent(batteries, zBlocks.SPRINKLER.get(),
                                                        zStatic.Blocks.sprinkler,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_sprinkler",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.SPRINKLER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_sprinkler")))
                                        .save(h, ID + ":main/steel/sprinkler");

                        var lmg = DataGenUtil
                                        .getExistingParent(root_steel, zBlocks.LASER_MACHINE.get(),
                                                        zStatic.Lazers.machine_gun,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_lmg",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.LASER_MACHINE.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_lmg")))
                                        .save(h, ID + ":main/steel/lmg");

                        var mirror = DataGenUtil
                                        .getExistingParent(lmg, zBlocks.LASER_MIRROR.get(),
                                                        zStatic.Lazers.mirror,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_mirror",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.LASER_MIRROR.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_mirror")))
                                        .save(h, ID + ":main/steel/mirror");

                        var rotor = DataGenUtil
                                        .getExistingParent(mirror, zBlocks.LASER_ROTOR.get(),
                                                        zStatic.Lazers.rotor,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_rotor",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.LASER_ROTOR.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_rotor")))
                                        .save(h, ID + ":main/steel/rotor");

                        var basic_machine_frame = DataGenUtil
                                        .getExistingParent(root_steel, zBlocks.BASIC_MACHINE_FRAME.get(),
                                                        zStatic.DecorativeBlocks.MachineFrame.basic,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_basic_machine_frame",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.BASIC_MACHINE_FRAME.get()))
                                        .requirements(AdvancementRequirements
                                                        .allOf(List.of("craft_basic_machine_frame")))
                                        .save(h, ID + ":main/steel/basic_machine_frame");

                        var harvester = DataGenUtil
                                        .getExistingParent(basic_machine_frame, zBlocks.HARVESTER.get(),
                                                        zStatic.Blocks.harvester,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_harvester",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.HARVESTER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_harvester")))
                                        .save(h, ID + ":main/steel/harvester");

                        var advanced_machine_frame = DataGenUtil
                                        .getExistingParent(basic_machine_frame, zBlocks.ADVANCED_MACHINE_FRAME.get(),
                                                        zStatic.DecorativeBlocks.MachineFrame.advanced,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_advanced_machine_frame",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.ADVANCED_MACHINE_FRAME.get()))
                                        .requirements(AdvancementRequirements
                                                        .allOf(List.of("craft_advanced_machine_frame")))
                                        .save(h, ID + ":main/steel/advanced_machine_frame");

                        var reactor_controller = DataGenUtil
                                        .getExistingParent(advanced_machine_frame, zBlocks.REACTOR_CONTROLLER.get(),
                                                        zStatic.ReactorStuff.controller,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_qrc",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.REACTOR_CONTROLLER.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_qrc")))
                                        .save(h, ID + ":main/steel/quantum_reactor_controller");

                        var fuel_cell = DataGenUtil
                                        .getExistingParent(reactor_controller, zBlocks.REACTOR_FUEL_CELL.get(),
                                                        zStatic.ReactorStuff.fuel_cell,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_fuel_cell",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.REACTOR_FUEL_CELL.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_fuel_cell")))
                                        .save(h, ID + ":main/steel/fuel_cell");

                        var moderators = DataGenUtil
                                        .getExistingParent(reactor_controller, zBlocks.SIMPLE_MODERATOR.get(),
                                                        zStatic.ReactorStuff.moderator,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_moderators",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item()
                                                                                        .of(zItemTag.MODERATORS)
                                                                                        .build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_moderators")))
                                        .save(h, ID + ":main/steel/moderators");

                        var coolers = DataGenUtil
                                        .getExistingParent(reactor_controller, zBlocks.SHADOW_COOLER.get(),
                                                        zStatic.ReactorStuff.cooler,
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_coolers",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(ItemPredicate.Builder.item()
                                                                                        .of(zItemTag.COOLERS).build()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_coolers")))
                                        .save(h, ID + ":main/steel/coolers");

                        var alloy_smelter = DataGenUtil.machineAdvancement(basic_machine_frame, h,
                                        zMachines.ALLOY_SMELTER);
                        var casting = DataGenUtil.machineAdvancement(basic_machine_frame, h, zMachines.CASTING_FACTORY);
                        var compressor = DataGenUtil.machineAdvancement(basic_machine_frame, h, zMachines.COMPRESSOR);
                        var furnace = DataGenUtil.machineAdvancement(basic_machine_frame, h,
                                        zMachines.ELECTRIC_FURNACE);
                        var extractor = DataGenUtil.machineAdvancement(basic_machine_frame, h, zMachines.EXTRACTOR);
                        var macerator = DataGenUtil.machineAdvancement(basic_machine_frame, h, zMachines.MACERATOR);
                        var melter = DataGenUtil.machineAdvancement(basic_machine_frame, h, zMachines.MELTER);
                        var rock_crusher = DataGenUtil.machineAdvancement(basic_machine_frame, h,
                                        zMachines.ROCK_CRUSHER);

                        var plastic = DataGenUtil
                                        .getExistingParent(casting, zItems.PLASTIC.get(),
                                                        zStatic.ResourceMaterial.plastic,
                                                        AdvancementType.CHALLENGE, true, true, false)
                                        .addCriterion("craft_plastic",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zItems.PLASTIC.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_plastic")))
                                        .rewards(AdvancementRewards.Builder.experience(200))
                                        .save(h, ID + ":main/steel/plastic");

                        var pellet_uranium = DataGenUtil.fuelpelletAdvancement(fuel_cell, h,
                                        zItems.URANIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.uranium, false);

                        var pellet_thorium = DataGenUtil.fuelpelletAdvancement(pellet_uranium, h,
                                        zItems.THORIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.thorium, false);

                        var pellet_plutonium = DataGenUtil.fuelpelletAdvancement(pellet_thorium, h,
                                        zItems.PLUTONIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.plutonium, false);

                        var pellet_neptunium = DataGenUtil.fuelpelletAdvancement(pellet_plutonium, h,
                                        zItems.NEPTUNIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.neptunium, false);

                        var pellet_americium = DataGenUtil.fuelpelletAdvancement(pellet_neptunium, h,
                                        zItems.AMERICIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.americium, false);

                        var pellet_berkelium = DataGenUtil.fuelpelletAdvancement(pellet_americium, h,
                                        zItems.BERKELIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.berkelium, false);

                        var pellet_californium = DataGenUtil.fuelpelletAdvancement(pellet_berkelium, h,
                                        zItems.CALIFORNIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.californium, false);

                        var pellet_curium = DataGenUtil.fuelpelletAdvancement(pellet_californium, h,
                                        zItems.CURIUM_PELLET.get(),
                                        zStatic.ResourceMaterial.curium, true);

                }

        }

}
