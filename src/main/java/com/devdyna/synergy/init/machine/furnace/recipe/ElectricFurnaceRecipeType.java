package com.devdyna.synergy.init.machine.furnace.recipe;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeType;
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
public class ElectricFurnaceRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public ElectricFurnaceRecipeType(int ticks, int energy, Ingredient input,
            ItemStack output) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
    }

    public static ElectricFurnaceRecipeType of(int ticks, int energy, Ingredient input,
            ItemStack output) {
        return new ElectricFurnaceRecipeType(ticks, energy, input, output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<MonoItemInput>> getMachine() {
        return zMachines.ELECTRIC_FURNACE;
    }

    @Override
    public ItemStack getRecipeInput(MonoItemInput recipe) {
        return recipe.input();
    }

    public static class Serializer implements RecipeSerializer<ElectricFurnaceRecipeType> {

        public static final MapCodec<ElectricFurnaceRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(ElectricFurnaceRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(ElectricFurnaceRecipeType::getEnergy),

                Ingredient.CODEC.fieldOf("input").forGetter(ElectricFurnaceRecipeType::getInputItem),
                ItemStack.CODEC.fieldOf("output").forGetter(ElectricFurnaceRecipeType::getOutputItem))
                .apply(inst, ElectricFurnaceRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ElectricFurnaceRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, ElectricFurnaceRecipeType::getTime,
                        ByteBufCodecs.INT, ElectricFurnaceRecipeType::getEnergy,
                        Ingredient.CONTENTS_STREAM_CODEC, ElectricFurnaceRecipeType::getInputItem,
                        ItemStack.STREAM_CODEC, ElectricFurnaceRecipeType::getOutputItem,
                        ElectricFurnaceRecipeType::new);

        @Override
        public MapCodec<ElectricFurnaceRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ElectricFurnaceRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
