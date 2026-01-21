package com.devdyna.synergy.init.builder.tools;

import java.util.List;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.StonecutterRecipe;

@SuppressWarnings("null")
public class Chisel extends Item {

    public Chisel(Properties properties) {
        super(properties);
    }

    public Chisel() {
        super(new Item.Properties().stacksTo(1).component(zComponents.REGISTRY_ID, null));
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var block = level.getBlockState(c.getClickedPos());
        var player = c.getPlayer();
        var item = c.getItemInHand();
        var blockItem = x.item(block);
        var hand = c.getHand();

        if (player.isCrouching()) {
            var id = x.id(blockItem.getItem());
            if (id == null) {
                return InteractionResult.FAIL;
            }

            item.set(zComponents.REGISTRY_ID, id);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1f, 1f);
            player.swing(hand);
        } else {
            var id = item.get(zComponents.REGISTRY_ID);
            if (id == null) {
                return InteractionResult.FAIL;
            }

            var saved = x.get(id);

            List<RecipeHolder<StonecutterRecipe>> r = level.getRecipeManager()
                    .getRecipesFor(RecipeType.STONECUTTING,
                            new SingleRecipeInput(blockItem), level);

            if (r == null || r.isEmpty()) {
                return InteractionResult.FAIL;
            }

            for (int i = 0; i < r.size(); i++) {
                var recipe = r.get(i).value();

                var result = recipe.getResultItem(level.registryAccess()).copy();

                if (result.is(saved)) {

                    if (saved instanceof BlockItem bi) {
                        level.setBlockAndUpdate(pos, bi.getBlock().defaultBlockState());
                    } else {
                        level.removeBlock(pos, false);
                        level.addFreshEntity(
                                new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                        x.item(saved)));
                    }

                    if (result.getCount() > 1) {
                        for (int j = 0; j < result.getCount() - 1; j++) {
                            level.addFreshEntity(
                                    new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                            x.item(saved)));
                        }
                    }

                    player.swing(hand);
                    level.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1f, 1f);

                    return InteractionResult.SUCCESS;
                }

            }

        }
        return InteractionResult.FAIL;

    }

    @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + "." + zStatic.Items.chisel));
        var nbt = i.get(zComponents.REGISTRY_ID);

        if (nbt != null) {
            var item = x.get(nbt);
            t.add(
                    Component.translatable(item.getDescriptionId())
                            .withStyle(ChatFormatting.GREEN));
        }

    }

}
