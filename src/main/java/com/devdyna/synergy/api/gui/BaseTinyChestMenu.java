package com.devdyna.synergy.api.gui;

import com.devdyna.synergy.api.beLogic.ItemStorageBlock;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BaseTinyChestMenu extends BaseMenu {

    public ItemStorageBlock blockEntity;
    public Level level;

    protected BaseTinyChestMenu(MenuType<?> menuType, int containerId, BlockEntity be) {
        super(menuType, containerId, be);
        this.blockEntity = (ItemStorageBlock) be;
    }

    @Override
    public Block[] getValidBlock() {
        return new Block[] { getChestType() };
    }

    @Override
    public BlockEntity getBlockEntity() {
        return (BlockEntity) blockEntity;
    }

    public abstract Block getChestType();

}
