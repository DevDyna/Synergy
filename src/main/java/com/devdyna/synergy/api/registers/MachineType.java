package com.devdyna.synergy.api.registers;

import java.util.function.Function;
import java.util.function.Supplier;

import com.devdyna.synergy.api.machine.*;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class MachineType<
BLOCK extends BaseMachineBlock, 
BE extends BaseMachineBE, 
MENU extends BaseMachineMenu,
 RECIPE extends BaseMachineRecipeType<?>> {

    private final String id;

    private final DeferredHolder<Block, BLOCK> block;
    private final DeferredHolder<BlockEntityType<?>, BlockEntityType<BE>> be;
    private final DeferredHolder<Item, ?> item;
    private final DeferredHolder<MenuType<?>, MenuType<MENU>> menutype;

    private final RecipeRegister<RECIPE> recipe;

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
                () -> BlockEntityType.Builder.of(beFactory, this.block.get()).build(null));

        this.item = zMachines.xITEM_MACHINE.registerSimpleBlockItem(this.block);

        this.menutype = zMachines.xMENU_MACHINE.register(
                id,
                () -> IMenuTypeExtension.create(menuFactory));

        this.recipe = RecipeRegister.of(id, recipeSerializer);
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

    public RecipeRegister<RECIPE> recipe() {
        return recipe;
    }

    public String id() {
        return id;
    }

}
