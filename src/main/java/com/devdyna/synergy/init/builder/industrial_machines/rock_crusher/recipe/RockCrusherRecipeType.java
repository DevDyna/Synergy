package com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.recipe;

import java.util.List;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ItemFluidInput;
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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public class RockCrusherRecipeType extends BaseMachineRecipeType<ItemFluidInput> {

    private List<ChanceOutputItem> result;

    public RockCrusherRecipeType(int ticks, int energy, SizedFluidIngredient fluid, SizedIngredient input,
            List<ChanceOutputItem> result) {
        this.input = input;
        this.ticks = ticks;
        this.energy = energy;
        this.fluid_input = fluid;
        this.result = result;
    }

    public static RockCrusherRecipeType of(int ticks, int energy, SizedFluidIngredient fluid, SizedIngredient input,
            List<ChanceOutputItem> result) {
        return new RockCrusherRecipeType(ticks, energy, fluid, input, result);
    }

    public List<ChanceOutputItem> getResult() {
        return result;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<ItemFluidInput>> getMachine() {
        return zMachines.ROCK_CRUSHER;
    }

    @Override
    public ItemStack getRecipeInput(ItemFluidInput recipe) {
        return recipe.item();
    }

    @Override
    public SizedFluidIngredient getRecipeFluidInput(ItemFluidInput recipe) {
        return x.fluidSized(recipe.input());
    }

    public static class Serializer implements RecipeSerializer<RockCrusherRecipeType> {

        public static final MapCodec<RockCrusherRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(RockCrusherRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(RockCrusherRecipeType::getEnergy),

                SizedFluidIngredient.FLAT_CODEC.fieldOf("input_fluid").forGetter(RockCrusherRecipeType::getFluidInput),
                SizedIngredient.FLAT_CODEC.fieldOf("input_item").forGetter(RockCrusherRecipeType::getInputItem),
                ChanceOutputItem.CODEC.listOf().fieldOf("result").forGetter(RockCrusherRecipeType::getResult))
                .apply(inst, RockCrusherRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RockCrusherRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, RockCrusherRecipeType::getTime,
                        ByteBufCodecs.INT, RockCrusherRecipeType::getEnergy,
                        SizedFluidIngredient.STREAM_CODEC, RockCrusherRecipeType::getFluidInput,
                        SizedIngredient.STREAM_CODEC, RockCrusherRecipeType::getInputItem,
                        ChanceOutputItem.STREAM_CODEC.apply(ByteBufCodecs.list(9)), RockCrusherRecipeType::getResult,
                        RockCrusherRecipeType::new);

        @Override
        public MapCodec<RockCrusherRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RockCrusherRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
