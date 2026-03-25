package com.devdyna.synergy.compat.jei.categories;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import java.util.function.Function;

import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.copper_oxidation.CopperOxidationRecipe;
import com.devdyna.synergy.common.recipes.copper_oxidation.OxidationStatus;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.init.types.zRecipeTypes;
import mekanism.common.lib.Color;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public class CopperOxidationCategory extends BaseRecipeCategory<CopperOxidationRecipe> {

    private List<Block> oxidable = x.getBlocks(NeoForgeDataMaps.OXIDIZABLES);
    private List<Block> waxable = x.getBlocks(NeoForgeDataMaps.WAXABLES);

    public CopperOxidationCategory(IGuiHelper h) {
        super(h);
    }

    public static final RecipeType<RecipeHolder<CopperOxidationRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.COPPER_OXIDATION.getType());

    @Override
    public RecipeType<RecipeHolder<CopperOxidationRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return "copper_oxidation";
    }

    @Override
    public ItemLike getIconItem() {
        return zItems.REDSTONE_ACID.get();
    }

    @Override
    public Size setXY() {
        return Size.of(77, 39);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/oxidation.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CopperOxidationRecipe recipe, IFocusGroup focuses) {

        super.setRecipe(builder, recipe, focuses);

        Ingredient input = null;
        Ingredient output = null;
        Ingredient catalyst = recipe.getCatalyst();

        switch (recipe.getOxidationType()) {
            case OxidationStatus.SCRAPPING:
                input = mapBlocks(oxidable, DataMapHooks::getNextOxidizedStage);
                output = x.ingredient(oxidable.stream().map(Block::asItem).toList());
                break;

            case OxidationStatus.OXIDIZING:
                input = x.ingredient(oxidable.stream().map(Block::asItem).toList());
                output = mapBlocks(oxidable, DataMapHooks::getNextOxidizedStage);
                break;

            case OxidationStatus.WAXING:
                input = x.ingredient(waxable.stream().map(Block::asItem).toList());
                output = mapBlocks(waxable, DataMapHooks::getBlockWaxed);
                break;

            case OxidationStatus.UNWAXING:
                input = mapBlocks(waxable, DataMapHooks::getBlockWaxed);
                output = x.ingredient(waxable.stream().map(Block::asItem).toList());
                break;
        }

        if (input != null)
            builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                    .addIngredients(input);

        if (catalyst != null) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 29, 2)
                    .addIngredients(catalyst);

        }

        if (output != null)
            builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 2)
                    .addIngredients(output);

        if (recipe.getOxidationType().equals(OxidationStatus.SCRAPPING))
            builder.addSlot(RecipeIngredientRole.OUTPUT, 59, 21)
                    .addItemStack(x.item(zItems.PATINA))
                    .addRichTooltipCallback((v, t) -> t.add(Component.translatable(ID + ".jei.patina_drop", "0-2")));
    }

    private Ingredient mapBlocks(List<Block> blocks, Function<Block, Block> f) {
        return x.ingredient(blocks.stream().map(f).map(Block::asItem).toArray(ItemLike[]::new));
    }

    @Override
    public void draw(CopperOxidationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        if (recipe.getOxidationType().equals(OxidationStatus.SCRAPPING)) {
            Image.of()
                    .rl(x.mcLoc("textures/gui/sprites/container/slot.png"))
                    .size(18, 18)
                    .offset(58, 20)
                    .render(helper, guiGraphics);

            guiGraphics.drawCenteredString(font, "0-2", 67, 30, Color.WHITE.rgb());

        }

    }

    @Override
    public void background(GuiGraphics graphics) {
        Image.of()
                .rl(this.setBackGround())
                .size(77, 20)
                .render(helper, graphics);
    }

}
