package com.devdyna.synergy.common.recipes.type.node_providers;

import com.devdyna.synergy.api.codec.recipe.NodePattern;
import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.input.ProviderInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

@SuppressWarnings({ "null" })
public class ItemProviderRecipe<T> extends BaseProviderRecipe<ItemStack> {

    // private final ItemStack output;

    public ItemProviderRecipe(NodePattern pattern, ItemStack output) {
        super(pattern, output);
        this.output = output;

    }

    public ItemStack assemble(ProviderInput i, HolderLookup.Provider r) {
        return this.output;
    }

    @Override
    public RecipeRegister<ItemProviderRecipe<ItemStack>> getRecipe() {
        return zRecipeTypes.ITEM_PROVIDER;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.ITEM_PROVIDER.get().asItem();
    }

    public static class Serializer implements RecipeSerializer<ItemProviderRecipe<ItemStack>> {

        public static final MapCodec<ItemProviderRecipe<ItemStack>> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
                .group(
                        NodePattern.CODEC.fieldOf("pattern").forGetter(ItemProviderRecipe::getPattern),
                        ItemStack.CODEC.fieldOf("result").forGetter(ItemProviderRecipe::getOutput))
                .apply(inst, ItemProviderRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ItemProviderRecipe<ItemStack>> STREAM_CODEC = StreamCodec
                .composite(
                        NodePattern.STREAM_CODEC, ItemProviderRecipe::getPattern,
                        ItemStack.STREAM_CODEC, ItemProviderRecipe::getOutput,
                        ItemProviderRecipe::new);

        @Override
        public MapCodec<ItemProviderRecipe<ItemStack>> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ItemProviderRecipe<ItemStack>> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
