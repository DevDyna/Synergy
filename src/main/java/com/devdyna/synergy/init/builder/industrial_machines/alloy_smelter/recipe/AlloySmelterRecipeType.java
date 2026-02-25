package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.common.recipes.input.BiItemInput;
import com.devdyna.synergy.init.types.zMachines;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings("null")
public class AlloySmelterRecipeType extends BaseMachineRecipeType<BiItemInput> {

    public AlloySmelterRecipeType(int ticks, int energy, SizedIngredient  right,SizedIngredient  left,
            ItemStack output ) {
        this.input = right;
        this.optional_input = left;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
  
    }

    public static AlloySmelterRecipeType of(int ticks, int energy, SizedIngredient  right,
            SizedIngredient  left, ItemStack output) {
        return new AlloySmelterRecipeType(ticks, energy, right, left, output);
    }
    
    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<BiItemInput>> getMachine() {
        return zMachines.ALLOY_SMELTER;
    }

    @Override
    public ItemStack getRecipeInput(BiItemInput recipe) {
        return recipe.first();
    }

    @Override
    public ItemStack getRecipeInput2(BiItemInput recipe) {
        return recipe.second();
    }

    public static class Serializer implements RecipeSerializer<AlloySmelterRecipeType> {

        public static final MapCodec<AlloySmelterRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(AlloySmelterRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(AlloySmelterRecipeType::getEnergy),
                SizedIngredient.FLAT_CODEC.fieldOf("right").forGetter(AlloySmelterRecipeType::getInputItem),
                SizedIngredient.FLAT_CODEC.fieldOf("left").forGetter(AlloySmelterRecipeType::getCatalystItem),
                ItemStack.CODEC.fieldOf("output").forGetter(AlloySmelterRecipeType::getOutputItem))
                .apply(inst, AlloySmelterRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AlloySmelterRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, AlloySmelterRecipeType::getTime,
                        ByteBufCodecs.INT, AlloySmelterRecipeType::getEnergy,
                        SizedIngredient.STREAM_CODEC, AlloySmelterRecipeType::getInputItem,
                        SizedIngredient.STREAM_CODEC,AlloySmelterRecipeType::getCatalystItem,
                        ItemStack.STREAM_CODEC, AlloySmelterRecipeType::getOutputItem,
                        AlloySmelterRecipeType::new);

        @Override
        public MapCodec<AlloySmelterRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AlloySmelterRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
