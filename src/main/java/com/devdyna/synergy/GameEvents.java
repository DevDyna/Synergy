package com.devdyna.synergy;

import java.util.List;

import com.devdyna.synergy.common.events.*;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.PlaceableBrickEvent;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class GameEvents {

    public static void register(IEventBus bus) {

        List.of(
                CrookBreakBlocks.class,
                ItemUseRecipeEvent.class,
                PlaceableBrickEvent.class,
                ItemTooltipEvents.class,
                EntityInteractionEvent.class,
                VanillaHarvestable.class,
                RegisterBrewingRecipes.class,
                PatinaDropEvent.class,
                EnderEyeReturn.class)
                .forEach(NeoForge.EVENT_BUS::register);

        bus.addListener(Capability::register);
        bus.addListener(CreativeTabs::register);
        bus.addListener(GameEvents::common);

    }

    public static void common(final FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {

            ((FlowerPotBlock) Blocks.FLOWER_POT)
                    .addPlant(zBlocks.IRON_WOOD.getSapling().getId(),
                            zBlocks.IRON_WOOD.getFlowerPot());

        });
    }

}
