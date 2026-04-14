package com.devdyna.synergy.api.blockfactories.machine;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.render.helpers.FluidGUITank;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.UpgradeType;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("null")
public abstract class BaseMachineScreen<T extends BaseMachineMenu> extends BaseScreen<T> {

        public BaseMachineScreen(T menu, Inventory playerInventory, Component title) {
                super(menu, playerInventory, title);
        }

        @Override
        protected ResourceLocation background() {
                return x.rl("textures/gui/container/" + menu.getMachine().id() + ".png");
        }

        @Override
        protected @Nullable ResourceLocation arrow() {
                return x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png");
        }

        protected boolean whenAnimateArrow() {
                return menu.isCrafting();
        }

        protected int getScaledArrowProgress() {
                return menu.getScaledArrowProgress();
        }

        protected int getEnergyStored() {
                return menu.getEnergyStored();
        }

        protected int getMaxEnergy() {
                return menu.getMaxEnergy();
        }

        protected int getRemainProgress() {
                return menu.getRemainProgress();
        }

        protected int getFluidAmount() {
                return menu.getFluidAmount();
        }

        protected Fluid getFluid() {
                return menu.getFluid();
        }

        protected int getMaxFluidAmount() {
                return menu.getMaxFluidAmount();
        }

        protected int getEnergyUsage() {
                return menu.getEnergyUsage();
        }

        protected void renderUpgradesLabel(GuiGraphics guiGraphics, int xo, int yo) {
                guiGraphics.blit(
                                x.rl("textures/gui/container/upgrade_slots.png"),
                                getGuiLeft() + xo,
                                getGuiTop() + yo,
                                0, 0,
                                32, 86,
                                32, 86);
        }

        protected void renderTickProgress(GuiGraphics guiGraphics, int xo, int yo) {
                if (getRemainProgress() > 0)
                        guiGraphics.drawString(font, Component.literal((1 + getRemainProgress()) + " ticks"),
                                        getGuiLeft() + xo,
                                        getGuiTop() + yo,
                                        defaultToolTipColor.getRGB(), false);
        }

        protected void renderEnergyStorage(GuiGraphics guiGraphics, int xo, int yo) {

                guiGraphics.blit(
                                x.rl("textures/gui/container/energy.png"),
                                getGuiLeft() + xo,
                                getGuiTop() + yo,
                                0, 0,
                                18, 72,
                                36, 72);

                if (getMaxEnergy() > 0 && getEnergyStored() > 0) {

                        int slice = (getEnergyStored() * 72) / getMaxEnergy();

                        guiGraphics.blit(
                                        x.rl("textures/gui/container/energy.png"),
                                        getGuiLeft() + xo,
                                        getGuiTop() + yo + (72 - slice),
                                        18, 72 - slice,
                                        18, slice,
                                        36, 72);
                }

        }

        protected void renderFluidTank(GuiGraphics guiGraphics, int xo, int yo) {

                guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                                getGuiLeft() + xo,
                                getGuiTop() + yo,
                                0, 0,
                                18, 72,
                                36, 72);

                if (getMaxFluidAmount() > 0 && getFluidAmount() > 0)
                        FluidGUITank.of()
                                        .setFluid(getFluid())
                                        .setMaxCapacity(getMaxFluidAmount())
                                        .setAmount(getFluidAmount())
                                        .size(72, 16)
                                        .offset(getGuiLeft() + xo + 1, getGuiTop() + yo - 1)
                                        .render(guiGraphics);

                guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                                getGuiLeft() + xo,
                                getGuiTop() + yo,
                                18, 0,
                                18, 72,
                                36, 72);

        }

        @Override
        protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

                renderUpgradesLabel(guiGraphics, 172, 0);

                super.renderBg(guiGraphics, v, i, i1);

                renderTickProgress(guiGraphics, 68, 70);

                renderEnergyStorage(guiGraphics, 8, 5);

        }

        @Override
        public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {

                super.render(graphics, pMouseX, pMouseY, pPartialTick);
                if (Pos.of(getGuiLeft() + 8, getGuiTop() + 5).setSize(18, 72).test(pMouseX, pMouseY)) {

                        graphics.renderComponentTooltip(font,
                                        List.of(
                                                        Component.literal(
                                                                        (Screen.hasShiftDown() ? getEnergyStored()
                                                                                        : StringUtil.getFormatNoRound()
                                                                                                        .format(getEnergyStored()))
                                                                                        + " FE / " +
                                                                                        (Screen.hasShiftDown()
                                                                                                        ? getMaxEnergy()
                                                                                                        : StringUtil.getFormatNoRound()
                                                                                                                        .format(getMaxEnergy()))
                                                                                        + " FE"),
                                                        Component.literal(
                                                                        getEnergyUsage() <= 0 ? "No valid recipe found"
                                                                                        : ("Usage : " + (getMaxEnergy() <= getEnergyUsage()
                                                                                                        ? "§c"
                                                                                                        : "")
                                                                                                        + getEnergyUsage()
                                                                                                        + "§f FE/tick"))),

                                        pMouseX,
                                        pMouseY);
                }

                if (menu.getSlot(BaseMachineBE.SLOT_UPGRADE_1).getItem().isEmpty())
                        if (Pos.of(getGuiLeft() + 179, getGuiTop() + 7).setSize(18, 18).test(pMouseX, pMouseY)) {
                                graphics.renderComponentTooltip(font, calculateTooltipUpgrades(), pMouseX, pMouseY);
                        }
                if (menu.getSlot(BaseMachineBE.SLOT_UPGRADE_2).getItem().isEmpty())
                        if (Pos.of(getGuiLeft() + 179, getGuiTop() + 7 + 18).setSize(18, 18).test(pMouseX, pMouseY)) {
                                graphics.renderComponentTooltip(font, calculateTooltipUpgrades(), pMouseX, pMouseY);
                        }
                if (menu.getSlot(BaseMachineBE.SLOT_UPGRADE_3).getItem().isEmpty())
                        if (Pos.of(getGuiLeft() + 179, getGuiTop() + 7 + 18 + 18).setSize(18, 18).test(pMouseX,
                                        pMouseY)) {
                                graphics.renderComponentTooltip(font, calculateTooltipUpgrades(), pMouseX, pMouseY);
                        }
                if (menu.getSlot(BaseMachineBE.SLOT_UPGRADE_4).getItem().isEmpty())
                        if (Pos.of(getGuiLeft() + 179, getGuiTop() + 7 + 18 + 18 + 18).setSize(18, 18).test(pMouseX,
                                        pMouseY)) {
                                graphics.renderComponentTooltip(font, calculateTooltipUpgrades(), pMouseX, pMouseY);
                        }

        }

        @Override
        protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
                guiGraphics.drawString(this.font, this.title, this.titleLabelX + 57, this.titleLabelY,
                                defaultToolTipColor.getRGB(), false);
        }

        private List<Component> calculateTooltipUpgrades() {
                List<Component> result = new ArrayList<>();

                result.add(Component.translatable(ID + ".screen.upgrades"));
                for (UpgradeType upgrade : validUpgrades())
                        result.add(Component
                                        .translatable(ID + ".screen.modifier." + upgrade.name().toLowerCase(),
                                                        getConfigLimits(upgrade))
                                        .withStyle(getConfigLimits(upgrade) > getInstalledUpgradesOnSlots(upgrade)
                                                        ? ChatFormatting.GREEN
                                                        : (getConfigLimits(upgrade) < getInstalledUpgradesOnSlots(
                                                                        upgrade) ? ChatFormatting.RED
                                                                                        : ChatFormatting.YELLOW)));

                return result;
        }

        public List<UpgradeType> validUpgrades() {
                return DEFAULT_UPGRADES;
        }

        public static final List<UpgradeType> DEFAULT_UPGRADES = List.of(UpgradeType.ENERGY, UpgradeType.SPEED);

        public int getConfigLimits(UpgradeType type) {
                return switch (type) {
                        case UpgradeType.ENERGY -> Common.MACHINE_MAX_ENERGY_EFFICIENCY_UPGRADES_TYPE.get();
                        case UpgradeType.SPEED -> Common.MACHINE_MAX_SPEED_UPGRADES_TYPE.get();
                        case UpgradeType.LUCK -> Common.MACHINE_MAX_LUCK_UPGRADES_TYPE.get();
                        case UpgradeType.FLUID -> Common.MACHINE_MAX_FLUID_UPGRADES_TYPE.get();
                        default -> 0;
                };
        }

        public int getInstalledUpgradesOnSlots(UpgradeType type) {
                return List.of(
                                BaseMachineBE.SLOT_UPGRADE_1,
                                BaseMachineBE.SLOT_UPGRADE_2,
                                BaseMachineBE.SLOT_UPGRADE_3,
                                BaseMachineBE.SLOT_UPGRADE_4)
                                .stream()
                                .map(menu::getSlot)
                                .map(Slot::getItem)
                                .filter(item -> UpgradeComponents.has(item, type))
                                .mapToInt(ItemStack::getCount)
                                .sum();
        }

}
