package com.devdyna.synergy.api;

import java.util.function.Function;
import java.util.function.Supplier;

import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class MachineType<BLOCK extends Block, BE extends BlockEntity, MENU extends AbstractContainerMenu, RECIPE extends Recipe<?>> {

    private final String id;

    private final DeferredHolder<Block, BLOCK> block;
    private final DeferredHolder<BlockEntityType<?>, BlockEntityType<BE>> be;
    private final DeferredHolder<Item, ?> item;
    private final DeferredHolder<MenuType<?>, MenuType<MENU>> menutype;

    private final zRecipe<RECIPE> recipe;

    public MachineType(
            String id,
            Function<BlockBehaviour.Properties, BLOCK> blockFactory,
            BlockEntitySupplier<BE> beFactory,
            IContainerFactory<MENU> menuFactory,
            Supplier<? extends RecipeSerializer<RECIPE>> recipeSerializer) {

        this.id = id;

        this.block = zMachines.xBLOCKS_MACHINE.registerBlock(id, blockFactory);

        this.be = zMachines.xBE_MACHINE.register(
                id,
                () -> new BlockEntityType<>(
                        beFactory,
                        false,
                        this.block.get()));

        this.item = zMachines.xITEM_MACHINE.registerSimpleBlockItem(this.block);

        this.menutype = zMachines.xMENU_MACHINE.register(
                id,
                () -> IMenuTypeExtension.create(menuFactory));

        this.recipe = zRecipe.of(id, recipeSerializer);
    }

    public DeferredHolder<Block, BLOCK> block() {
        return block;
    }

    public DeferredHolder<BlockEntityType<?>, BlockEntityType<BE>> blockentity() {
        return be;
    }

    public DeferredHolder<Item, ?> item() {
        return item;
    }

    public DeferredHolder<MenuType<?>, MenuType<MENU>> menu() {
        return menutype;
    }

    public zRecipe<RECIPE> recipe() {
        return recipe;
    }

    public String id() {
        return id;
    }

}
