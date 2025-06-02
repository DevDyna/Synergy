package com.devdyna.synergy.init.builder.pipeBlocks.nodes;

import java.util.List;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.builder._core.nodeType;
import com.devdyna.synergy.init.builder._core.pipeType;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public class NodeBE extends BaseBE {

    public NodeBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public NodeBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.PIPE_NODE.get(), pos, blockState);
    }

    int i = 0;
    int delay = 40;

    @Override
    public void tickServer() {

        // TODO temp delayer
        if (i < 20)
            i++;

        if (i >= 20) {
            doRun();
            i = 0;
        }
    }

    public void doRun() {

        if (level == null)
            return;

        var input = getBlockState().getValue(nodeType.FACING);

        // if (getCap(level, input, getBlockPos().relative(input)) != null)
        // input
        // extract items

        var basepos = getBlockPos();
        IItemHandler output;
        BlockPos outputPos = basepos;

        var flag = true;

        var checked = List.of(basepos);
        int directions = 6;

        if (getCap(level, input, getBlockPos().relative(input)) != null) // TODO check if has items
            while (directions != 0 && flag) {

                for (Direction dir : pipeType.DIRECTIONS) {

                    if (getBlockPos().relative(input) == basepos)
                        continue;

                    if (getCap(level, dir, basepos) != null) {
                        output = getCap(level, dir, basepos);
                        outputPos = basepos;
                        flag = false;
                    }

                    if (level.getBlockState(basepos.relative(dir)).is(zBlockTag.PIPE_CONNECTORS)
                            && checked.indexOf(basepos) == -1) {
                        checked.add(basepos);
                        basepos = basepos.relative(dir);
                        directions = 6;
                    } else {
                        directions--;
                    }
                }
            }

        if (directions != 0) {
            if (outputPos == null)
                LogUtil.info("Null output pos");
            else
                LogUtil.info("outputFound : " + outputPos.toString());
        } else {
            LogUtil.info("no valid path found");
        }

        // TODO
        // for (int i = 0; i < cap.getSlots(); i++) {
        // cap.extractItem(i, i, true);
        // }

    }

    private IItemHandler getCap(Level l, Direction d, BlockPos pos) {
        return l != null && l.getBlockEntity(pos.relative(d)) != null
                ? Capabilities.ItemHandler.BLOCK.getCapability(l, pos.relative(d),
                        l.getBlockState(pos.relative(d)),
                        l.getBlockEntity(pos.relative(d)), d.getOpposite())
                : null;
    }

}
