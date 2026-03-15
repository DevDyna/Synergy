package com.devdyna.synergy.compat.jade.provider;

import java.util.Optional;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.node_pipe.IProvider;
import com.devdyna.synergy.api.node_pipe.nodeType;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.FluidStack;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum NodeProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config) {

        var server = accessor.getServerData();

        var helper = IElementHelper.get();
        try {
            if (server.contains("modid") && server.contains("path"))
                tooltip.add(helper.item(
                        x.item(BuiltInRegistries.ITEM
                                .get(x.rl(server.getString("modid"), server.getString("path")))))
                        .message(null));
        } catch (Exception e) {
            //catch possible fluids without bucket item
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        NodeBaseBE nodejs = (NodeBaseBE) accessor.getBlockEntity();

        ResourceLocation id = null;

        var pos = nodejs.getBlockPos().relative(nodejs.getBlockState().getValue(nodeType.FACING));

        if (nodejs instanceof IProvider ip) {
            var r = ((Optional<RecipeHolder<?>>) ip.getRecipe(pos));

            if (r.isEmpty() || r == null)
                return;

            var recipe = (BaseProviderRecipe<?>) r.get()
                    .value();

            if (recipe.getOutput() instanceof ItemStack stack)
                id = BuiltInRegistries.ITEM.getKey(stack.getItem());

            if (recipe.getOutput() instanceof FluidStack stack)
                id = BuiltInRegistries.ITEM.getKey(stack.getFluid().getBucket());

        }

        if (id != null) {
            data.putString("modid", id.getNamespace());
            data.putString("path", id.getPath());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return x.rl(zStatic.PipeStuff.nodes.type_provider);
    }

}