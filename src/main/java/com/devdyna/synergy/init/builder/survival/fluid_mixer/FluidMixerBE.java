package com.devdyna.synergy.init.builder.survival.fluid_mixer;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.FluidStorageTank;
import com.devdyna.synergy.api.basebe.be.TickingTankBE;
import com.devdyna.synergy.api.beLogic.EnvironmentModifier;
import com.devdyna.synergy.api.beLogic.FoundryFuelProvider;
import com.devdyna.synergy.api.beLogic.TimeredRecipe;
import com.devdyna.synergy.api.recipes.inputs.BiFluidInput;
import com.devdyna.synergy.api.utils.DirectionUtil;
import com.devdyna.synergy.api.utils.FluidUtil;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.builder.survival.fluid_mixer.recipe.FluidMixingRecipe;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

@SuppressWarnings("null")
public class FluidMixerBE extends TickingTankBE
        implements EnvironmentModifier, TimeredRecipe {

    public FluidMixerBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidMixerBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FLUID_MIXER.get(), pos, blockState);
    }

    private Ticker ticker = null;

    @Override
    public void tickServer() {

        if (level == null) {
            fail();
            return;
        }

        update();

        if (getFuelProvider() == null) {
            fail();
            return;
        }

        if (!getFuelProvider().initConditions()) {
            fail();
            return;
        }

        var neighbors = Arrays.stream(DirectionUtil.HORIZONTAL)
                .map(d -> level.getCapability(
                        Capabilities.FluidHandler.BLOCK,
                        getBlockPos().relative(d),
                        d.getOpposite()))
                .filter(h -> h != null)
                .toList();

        if (neighbors.size() < 2) {
            fail();
            return;
        }

        IFluidHandler tank1 = null;
        FluidStack fluid1 = null;
        IFluidHandler tank2 = null;
        FluidStack fluid2 = null;

        FluidMixingRecipe recipe = null;

        outer: for (int i = 0; i < neighbors.size(); i++) {
            for (int j = i + 1; j < neighbors.size(); j++) {

                var f1 = neighbors.get(i);
                var f2 = neighbors.get(j);

                var fluids1 = FluidUtil.getFluids(f1);
                var fluids2 = FluidUtil.getFluids(f2);

                if (fluids1.isEmpty() || fluids2.isEmpty())
                    continue;

                for (var fs1 : fluids1) {
                    for (var fs2 : fluids2) {

                        var r1 = level.getRecipeManager().getRecipeFor(
                                zRecipeTypes.FLUID_MIXING.getType(),
                                new BiFluidInput(fs1, fs2),
                                level);

                        if (r1.isPresent()) {
                            tank1 = f1;
                            tank2 = f2;
                            recipe = r1.get().value();
                            fluid1 = fs1.copyWithAmount(recipe.getFirst().amount());
                            fluid2 = fs2.copyWithAmount(recipe.getSecond().amount());
                            break outer;
                        }

                        var r2 = level.getRecipeManager().getRecipeFor(
                                zRecipeTypes.FLUID_MIXING.getType(),
                                new BiFluidInput(fs2, fs1),
                                level);

                        if (r2.isPresent()) {
                            tank1 = f2;
                            tank2 = f1;
                            recipe = r2.get().value();
                            fluid1 = fs2.copyWithAmount(recipe.getFirst().amount());
                            fluid2 = fs1.copyWithAmount(recipe.getSecond().amount());
                            break outer;
                        }
                    }
                }
            }
        }

        if (recipe == null) {
            fail();
            return;
        }

        if (tank1.drain(fluid1, FluidAction.SIMULATE).getAmount() < recipe.getFirst().amount()
                || tank2.drain(fluid2, FluidAction.SIMULATE).getAmount() < recipe.getSecond().amount()) {
            fail();
            return;
        }

        if (getFluidStorage().fill(recipe.getOutput(), FluidAction.SIMULATE) == 0) {
            fail();
            return;
        }

        if (ticker == null)
            ticker = Ticker.of((int) Math.max(1, recipe.getTicks() / getTickerSpeed()));

        if (ticker.commit()) {

            tank1.drain(fluid1, FluidAction.EXECUTE);
            tank2.drain(fluid2, FluidAction.EXECUTE);

            getFluidStorage().fill(recipe.getOutput().copy(), FluidAction.EXECUTE);
            getFuelProvider().executeOnRecipeCompleted();
        }

    }

    private void fail() {
        update();
        ticker = null;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("tank", getFluidStorage().serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        getFluidStorage().deserializeNBT(registries, tag.getCompound("tank"));
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

    public @Nullable FoundryFuelProvider getFuelProvider() {
        return (level.getBlockEntity(getBlockPos().below()) instanceof FoundryFuelProvider provider)
                ? provider
                : null;
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
    public float getSpeedModifier() {
        return getFuelProvider() == null ? 0 : getFuelProvider().getSpeedModifier();
    }

    @Override
    public boolean isRequired() {
        return true;
    }

    @Override
    public String failDescKey() {
        return ID + ".jade.modifier.environment.fuel.fail";
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public float getTickerSpeed() {
        return Math.max(1, getSpeedModifier());
    }

}
