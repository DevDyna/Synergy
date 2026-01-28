package com.devdyna.synergy.init.builder.industrial_machines.caster.recipe;

import java.util.Optional;
import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.FluidInput;
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
public class CasterRecipeType extends BaseMachineRecipeType<FluidInput> {

    public CasterRecipeType(int ticks, int energy, SizedFluidIngredient fluid, SizedIngredient input,
            boolean consumeCatalyst, ItemStack output) {
        this.input = input;
        this.ticks = ticks;
        this.energy = energy;
        this.fluid_input = fluid;
        this.output = output;
        this.consumeCatalyst = consumeCatalyst;
    }

    public static CasterRecipeType of(int ticks, int energy, SizedFluidIngredient fluid, SizedIngredient input,
            boolean consumeCatalyst, ItemStack output) {
        return new CasterRecipeType(ticks, energy, fluid, input, consumeCatalyst, output);
    }

    @Override
    public boolean hasSecondaryOutput() {
        return false;
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<FluidInput>> getMachine() {
        return zMachines.CASTING_FACTORY;
    }

    @Override
    public ItemStack getRecipeInput(FluidInput recipe) {
        return recipe.item();
    }

    @Override
    public SizedFluidIngredient getRecipeFluidInput(FluidInput recipe) {
        return x.fluidSized(recipe.input());
    }

    public static class Serializer implements RecipeSerializer<CasterRecipeType> {

        public static final MapCodec<CasterRecipeType> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CasterRecipeType::getTime),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("energy").forGetter(CasterRecipeType::getEnergy),

                SizedFluidIngredient.FLAT_CODEC.fieldOf("input_fluid").forGetter(CasterRecipeType::getFluidInput),

                SizedIngredient.FLAT_CODEC.optionalFieldOf("input_item",x.itemSized())
                        .forGetter(r->(r.getInputItem() == null || x.getItems(r.getInputItem()).isEmpty()) ? x.itemSized() : r.getInputItem()),
                Codec.BOOL.fieldOf("consume_item").forGetter(CasterRecipeType::consumeCatalyst),
                ItemStack.CODEC.fieldOf("output").forGetter(CasterRecipeType::getOutputItem)

        )
                .apply(inst, CasterRecipeType::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CasterRecipeType> STREAM_CODEC = StreamCodec
                .composite(

                        ByteBufCodecs.INT, CasterRecipeType::getTime,
                        ByteBufCodecs.INT, CasterRecipeType::getEnergy,
                        SizedFluidIngredient.STREAM_CODEC, CasterRecipeType::getFluidInput,

                        ByteBufCodecs.optional(SizedIngredient.STREAM_CODEC),
                        r -> (r.getInputItem() == null || x.getItems(r.getInputItem()).isEmpty())
                                ? Optional.empty()
                                : Optional.of(r.getInputItem()),

                        ByteBufCodecs.BOOL, CasterRecipeType::consumeCatalyst,

                        ItemStack.STREAM_CODEC, CasterRecipeType::getOutputItem,

                        (ticks, energy, f, i, o, c) -> new CasterRecipeType(
                                ticks,
                                energy,
                                f,
                                i.orElse(null),
                                o, c));

        @Override
        public MapCodec<CasterRecipeType> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CasterRecipeType> streamCodec() {
            return STREAM_CODEC;
        }

    }

}
