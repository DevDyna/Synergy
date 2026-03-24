package com.devdyna.synergy.common.events;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zPotions;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class RegisterBrewingRecipes {
        @SubscribeEvent
        public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {

                PotionBrewing.Builder b = event.getBuilder();

                b.addMix(
                                Potions.AWKWARD,
                                zItems.VENOM_SAC.get(),
                                Potions.POISON);

                b.addMix(
                                Potions.POISON,
                                zItems.WITHERFLESH.get(),
                                zPotions.WITHERING);

                b.addMix(
                                Potions.AWKWARD,
                                zItems.SLIME_BOLUS.get(),
                                Potions.OOZING);

                b.addMix(
                                Potions.WATER_BREATHING,
                                zItems.GUARDIAN_SCALE.get(),
                                zPotions.CONDUIT);

                b.addMix(
                                Potions.AWKWARD,
                                zItems.SILVERFISH_DUST.get(),
                                Potions.INFESTED);

                b.addMix(
                                Potions.WEAKNESS,
                                zItems.ZOMBIE_LIVER.get(),
                                zPotions.HUNGER);

                b.addRecipe(
                                x.ingredient(Items.HONEY_BOTTLE),
                                x.ingredient(Items.REDSTONE),
                                x.item(zItems.REDSTONE_ACID.get()));
        }
}
