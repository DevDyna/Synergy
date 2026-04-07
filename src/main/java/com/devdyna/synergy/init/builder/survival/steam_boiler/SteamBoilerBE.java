package com.devdyna.synergy.init.builder.survival.steam_boiler;

import java.util.Optional;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingTankBE;
import com.devdyna.synergy.api.beLogic.HeatConsumer;
import com.devdyna.synergy.api.beLogic.HeatProvider;
import com.devdyna.synergy.api.beLogic.RestrictedFluidHandler;
import com.devdyna.synergy.api.beLogic.SimpleTickerDelay;
import com.devdyna.synergy.api.recipes.inputs.FluidInput;
import com.devdyna.synergy.api.utils.BiBool;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.api.utils.Range;
import com.devdyna.synergy.api.utils.RecipeUtils;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.builder.survival.steam_boiler.recipe.SteamBoilerRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zFluidTags;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class SteamBoilerBE extends TickingTankBE
        implements RestrictedFluidHandler, HeatConsumer, SimpleTickerDelay {

    protected final FluidStorageTank STEAMTANK = new FluidStorageTank(this, DEFAULT_TANK_STORAGE,
            (f) -> f.is(zFluidTags.STEAM));

    public SteamBoilerBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public SteamBoilerBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.STEAM_BOILER.get(), pos, blockState);
    }

    private Ticker ticker = null;

    @Override
    public void tickServer() {

        update();

        Optional<RecipeHolder<SteamBoilerRecipe>> r = level.getRecipeManager()
                .getRecipeFor(zRecipeTypes.STEAM_BOILER.getType(),
                        new FluidInput(getFluidStorage().getFluid()),
                        level);

        if (!r.isPresent()) {
            reset();
            return;
        }

        var recipe = r.get().value();

        if (getFluidStorage().drain(getFluidStorage().getFluid(), FluidAction.SIMULATE).getAmount() < recipe.getInput()
                .amount()) {
            reset();
            return;
        }

        if (STEAMTANK.fill(recipe.getOutput(), FluidAction.SIMULATE) != recipe.getOutput().getAmount()) {
            reset();
            return;
        }

        processHeat();

    }

    @Override
    public void whenAbove(int heat) {

        if (LevelUtil.chance(5, level))
            level.explode(null,
                    getBlockPos().getX() + 0.5,
                    getBlockPos().getY() + 0.5,
                    getBlockPos().getZ() + 0.5,
                    (0.025f * (heat - 200)) + 1.25f,
                    ExplosionInteraction.BLOCK);
    }

    @Override
    public void whenInRange(int heat) {

        var recipe = RecipeUtils.getUnsafeRecipes(level, zRecipeTypes.STEAM_BOILER,
                new FluidInput(getFluidStorage().getFluid()));

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks());

        if (ticker != null && ticker.commit()) {

            getFluidStorage().drain(recipe.getInput().amount(), FluidAction.EXECUTE);
            STEAMTANK.fill(recipe.getOutput(), FluidAction.EXECUTE);
            reset();
        }

    }

    public void reset() {
        if (ticker != null)
            ticker = null;
        update();
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("water_tank", getFluidStorage().serializeNBT(registries));
        tag.put("steam_tank", STEAMTANK.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("water_tank"));
        STEAMTANK.deserializeNBT(registries, tag.getCompound("steam_tank"));
        super.loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public FluidStorageTank getFluidStorage() {
        return getData(zHandlers.FLUID_TANK);
    }

    @Override
    public int getFluidCapacity() {
        return 16_000;
    }

    @Override
    public IFluidHandler getFluidStorageRestricted() {
        return new IFluidHandler() {

            @Override
            public int getTanks() {
                return 2;
            }

            @Override
            public FluidStack getFluidInTank(int tank) {
                return switch (tank) {
                    case 0 -> getFluidStorage().getFluid();
                    case 1 -> STEAMTANK.getFluid();
                    default -> FluidStack.EMPTY;
                };
            }

            @Override
            public int getTankCapacity(int tank) {
                return DEFAULT_TANK_STORAGE;
            }

            @Override
            public boolean isFluidValid(int tank, FluidStack stack) {
                switch (tank) {
                    case 0:
                        return level.getRecipeManager()
                                .getRecipeFor(zRecipeTypes.STEAM_BOILER.getType(),
                                        new FluidInput(stack),
                                        level)
                                .isPresent();

                    default:
                        return false;
                }

            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {

                Optional<RecipeHolder<SteamBoilerRecipe>> r = level.getRecipeManager()
                        .getRecipeFor(zRecipeTypes.STEAM_BOILER.getType(),
                                new FluidInput(resource),
                                level);

                if (r.isPresent()) {
                    int filled = getFluidStorage().fill(resource, action);
                    if (filled > 0 && action.execute())
                        update();
                    return filled;
                }
                return 0;
            }

            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                FluidStack drained = STEAMTANK.drain(resource, action);
                if (!drained.isEmpty() && action.execute())
                    update();
                return drained;
            }

            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                FluidStack drained = STEAMTANK.drain(maxDrain, action);
                if (!drained.isEmpty() && action.execute())
                    update();
                return drained;
            }
        };
    }

    @Override
    public Range getHeatLimit() {
        return Range.of(100, 200, BiBool.of(true, true));
    }

    @Override
    public HeatProvider getProvider() {
        return level.getBlockEntity(getBlockPos().below()) instanceof HeatProvider be ? be : null;
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

}
