package com.devdyna.synergy.common.recipes.copper_oxidation;

import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.copper_oxidation.OxidationStatus.OxidationInput;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class CopperOxidationRecipe extends BaseRecipeType<OxidationInput> {

    private OxidationStatus type;

    public CopperOxidationRecipe(OxidationStatus type) {
        this.type = type;
    }

    public boolean matches(OxidationInput r, Level l) {
        return r.type().equals(type);
    }

    public ItemStack assemble(OxidationInput i, HolderLookup.Provider r) {
        return i.getItem(0);
    }

    public OxidationStatus getOxidationType() {
        return type;
    }

    @Override
    public Item getToastIcon() {
        return Items.COPPER_BLOCK;
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(getToastIcon())));
    }

    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(getToastIcon());
    }

    @Override
    public RecipeRegister<CopperOxidationRecipe> getRecipe() {
        return zRecipeTypes.COPPER_OXIDATION;
    }

    public static class Serializer implements RecipeSerializer<CopperOxidationRecipe> {

        public static final MapCodec<CopperOxidationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                OxidationStatus.CODEC.fieldOf("step").forGetter(CopperOxidationRecipe::getOxidationType))
                .apply(inst, CopperOxidationRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CopperOxidationRecipe> STREAM_CODEC = StreamCodec
                .composite(
                        OxidationStatus.STREAM_CODEC, CopperOxidationRecipe::getOxidationType,
                        CopperOxidationRecipe::new);

        @Override
        public MapCodec<CopperOxidationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CopperOxidationRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }
}
