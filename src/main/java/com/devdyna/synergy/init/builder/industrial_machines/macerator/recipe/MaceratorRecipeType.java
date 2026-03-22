package com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.codec.recipe.ChanceOutputItem;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
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
public class MaceratorRecipeType extends BaseMachineRecipeType<MonoItemInput> {

    public MaceratorRecipeType(int ticks, int energy, SizedIngredient input,
            ItemStack output, ChanceOutputItem secondary) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
        this.energy = energy;
        this.optional_output_item = secondary;
    }

    public static MaceratorRecipeType of(int ticks, int energy, SizedIngredient input,
            ItemStack output, ChanceOutputItem secondary) {
        return new MaceratorRecipeType(ticks, energy, input, output, secondary);
    }

    // @Override
    // public boolean hasSecondaryOutput() {
    //     return true;
    // }

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

                SizedIngredient.FLAT_CODEC.fieldOf("input").forGetter(MaceratorRecipeType::getInputItem),
                ItemStack.CODEC.fieldOf("output").forGetter(MaceratorRecipeType::getOutputItem),
                ChanceOutputItem.CODEC.optionalFieldOf("secondary")
                        .forGetter(r -> ChanceOutputItem.optional(r.getSecondaryOutputItem())))
                .apply(inst, (ticks, energy, input, output, secondary) -> new MaceratorRecipeType(
                        ticks,
                        energy,
                        input,
                        output,
                        secondary.orElse(null))));

        public static final StreamCodec<RegistryFriendlyByteBuf, MaceratorRecipeType> STREAM_CODEC = StreamCodec
                .composite(
                        ByteBufCodecs.INT, MaceratorRecipeType::getTime,
                        ByteBufCodecs.INT, MaceratorRecipeType::getEnergy,
                        SizedIngredient.STREAM_CODEC, MaceratorRecipeType::getInputItem,
                        ItemStack.STREAM_CODEC, MaceratorRecipeType::getOutputItem,
                        ByteBufCodecs.optional(ChanceOutputItem.STREAM_CODEC),
                        r -> ChanceOutputItem.optional(r.getSecondaryOutputItem()),
                        (ticks, energy, input, output, secondary) -> new MaceratorRecipeType(
                                ticks,
                                energy,
                                input,
                                output,
                                secondary.orElse(null)));

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
