package com.devdyna.synergy.api.utils;

import java.util.List;
import java.util.Optional;

import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.registers.RecipeRegister;

import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class RecipeUtils {

    public static <T extends Recipe<I>, I extends RecipeInput> List<RecipeHolder<T>> getRecipes(RecipeType<T> r) {
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(r);
    }

    public static <T extends BaseMachineRecipeType<I>, I extends RecipeInput> Optional<RecipeHolder<T>> getRecipes(
            Level level, MachineType<?, ?, ?, T> m, I input) {
        return level.getRecipeManager().getRecipeFor(m.recipe().getType(), input, level);
    }

    public static <T extends BaseMachineRecipeType<I>, I extends RecipeInput> T getUnsafeRecipes(Level level,
            MachineType<?, ?, ?, T> m, I input) {
        return level.getRecipeManager().getRecipeFor(m.recipe().getType(), input, level).get().value();
    }

    public static <T extends Recipe<I>, I extends RecipeInput> List<RecipeHolder<T>> getRecipes(RecipeRegister<T> r) {
        return getRecipes(r.getType());
    }

    public static <T extends BaseMachineRecipeType<I>, I extends RecipeInput> List<RecipeHolder<T>> getRecipes(
            MachineType<?, ?, ?, T> r) {
        return getRecipes(r.recipe().getType());
    }

    public static <T extends Recipe<I>, I extends RecipeInput> void registerCategory(IRecipeRegistration i,
            RecipeRegister<T> r) {
        i.addRecipes(mezz.jei.api.recipe.RecipeType.createFromVanilla(r.getType()),
                RecipeUtils.getRecipes(r.getType()));
    }

    public static FluidStack optionalCodec(FluidStack f) {
        return (f == null || f.isEmpty())
                ? FluidStack.EMPTY
                : f;
    }

    public static ItemStack optionalCodec(ItemStack i) {
        return (i == null || i.isEmpty())
                ? ItemStack.EMPTY
                : i;
    }

}
