package com.devdyna.synergy.init.machine.macerator.recipe;

import java.util.Optional;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

@SuppressWarnings("null")
public class MaceratorRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public MaceratorRecipeType(int ticks, int energy, Ingredient input,
            ItemStack output, ItemStack secondary, float chance) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
        this.secondary = secondary;
        this.chance = chance;
    }

    public static MaceratorRecipeType of(int ticks, int energy, Ingredient input,
            ItemStack output, ItemStack secondary, float chance) {
        return new MaceratorRecipeType(ticks, energy, input, output, secondary, chance);
    }

    @Override
    public boolean hasSecondaryItem() {
        return true;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<MonoItemInput>> getMachine() {
        return zMachines.MACERATOR;
    }

    @Override
    public ItemStack getRecipeInput(MonoItemInput recipe) {
        return recipe.input();
    }

    public static class Serializer implements RecipeSerializer<MaceratorRecipeType> {

        public static final MapCodec<MaceratorRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(MaceratorRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(MaceratorRecipeType::getEnergy),

                Ingredient.CODEC.fieldOf("input").forGetter(MaceratorRecipeType::getInputItem),
                ItemStack.CODEC.fieldOf("output").forGetter(MaceratorRecipeType::getOutputItem),
                ItemStack.CODEC.optionalFieldOf("secondary", ItemStack.EMPTY)
                        .forGetter(r -> (r.getSecondaryOutputItem() == null || r.getSecondaryOutputItem().isEmpty())
                                ? ItemStack.EMPTY
                                : r.getSecondaryOutputItem()),
                Codec.floatRange(0, 1).fieldOf("chance").forGetter(MaceratorRecipeType::getSecondaryItemChance))
                .apply(inst, MaceratorRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MaceratorRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, MaceratorRecipeType::getTime,
                        ByteBufCodecs.INT, MaceratorRecipeType::getEnergy,
                        Ingredient.CONTENTS_STREAM_CODEC, MaceratorRecipeType::getInputItem,
                        ItemStack.STREAM_CODEC, MaceratorRecipeType::getOutputItem,
                        ByteBufCodecs.optional(ItemStack.STREAM_CODEC),
                        r -> (r.getSecondaryOutputItem() == null || r.getSecondaryOutputItem().isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getSecondaryOutputItem()),
                        ByteBufCodecs.FLOAT, MaceratorRecipeType::getSecondaryItemChance,
                        (t, e, i, o, s, c) -> new MaceratorRecipeType(t, e, i, o, s.orElse(ItemStack.EMPTY), c));

        @Override
        public MapCodec<MaceratorRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MaceratorRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
