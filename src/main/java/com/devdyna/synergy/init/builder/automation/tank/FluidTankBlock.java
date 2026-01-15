package com.devdyna.synergy.init.builder.automation.tank;

import static com.devdyna.synergy.Main.ID;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.block.BlockTank;
import com.devdyna.synergy.api.beLogic.KeepInventory;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.datagen.client.DataLang;
import com.devdyna.synergy.init.types.zComponents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

@SuppressWarnings("null")
public class FluidTankBlock extends BlockTank {

    public FluidTankBlock(Properties properties) {
        super(properties.noOcclusion().strength(1.0f).sound(SoundType.GLASS));
    }

    public FluidTankBlock() {
        this(BlockBehaviour.Properties.of());
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FluidTankBE(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity,
            ItemStack stack) {

        super.setPlacedBy(level, pos, state, entity, stack);

        if (level.getBlockEntity(pos) instanceof KeepInventory keep)
            keep.whenPlaced(level, pos, entity, stack);

    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, Builder builder) {
        if (builder.getParameter(LootContextParams.BLOCK_ENTITY) instanceof KeepInventory keep) {
            var drops = keep.getDropItems(this, state, builder);
            if (drops != null)
                return drops;
        }

        return super.getDrops(state, builder);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        var nbt = stack.get(zComponents.MACHINE_DATA);
        if (nbt != null) {
            var copied = nbt.copyTag();

            if (copied.contains("neoforge:attachments")) {
                CompoundTag nbtAttachments = copied.getCompound("neoforge:attachments");

                if (nbtAttachments.contains("synergy:fluid_tank")) {
                    CompoundTag nbtTank = nbtAttachments.getCompound("synergy:fluid_tank");

                    if (nbtTank.contains("Fluid")) {
                        CompoundTag nbtFluid = nbtTank.getCompound("Fluid");

                        int amount = nbtFluid.getInt("amount");

                        var fluid = BuiltInRegistries.FLUID.getOptional(x.parse(nbtFluid.getString("id")));

                        if (!fluid.isEmpty()) {
                            tooltip.add(Component
                                    .literal(
                                            DataLang.TIP_COLOR + fluid.get().getFluidType().getDescription().getString()
                                                    + " : " + amount + "mB"));
                            return;
                        }

                    }
                }
            }
        }

        tooltip.add(Component.translatable(ID + ".tank_interact.empty").withStyle(ChatFormatting.GRAY));

    }

}
