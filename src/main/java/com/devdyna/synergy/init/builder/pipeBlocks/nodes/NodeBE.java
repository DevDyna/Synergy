package com.devdyna.synergy.init.builder.pipeBlocks.nodes;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.builder._core.nodeType;
import com.devdyna.synergy.init.builder._core.pipeProperties;
import com.devdyna.synergy.init.builder._core.pipeType;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
    int delay = 80;

    @Override
    public void tickServer() {

        // TODO temp delayer
        if (i < delay)
            i++;

        if (i >= delay) {
            doRun();
            i = 0;
        }
    }

    public void doRun() {

        if (level == null)
            return;

        BlockPos variablePos = getBlockPos();
        Direction outputSide = null;
        BlockPos outputPos = null;
        BlockState actual;
        BlockState offset;
        var totalCheckSide = 6;
        List<BlockPos> validPath = new ArrayList<>();

        var input = getBlockState().getValue(nodeType.FACING);
        var inCap = getCap(level, input, getBlockPos().relative(input));

        var flag = false;

        if (inCap == null) {
            LogUtil.info("##ERROR## input NULL");
            return;
        } else
            for (int i = 0; i < inCap.getSlots(); i++) {
                if (!inCap.extractItem(i, 1, true).isEmpty()) {
                    flag = true;
                    break;
                }
            }

        while (flag) {

            LogUtil.info("#### start directions ####");

            for (Direction dir : Direction.values()) {
                actual = level.getBlockState(variablePos);
                offset = level.getBlockState(variablePos.relative(dir));

                LogUtil.info("#1 INFO" +
                        "\n direction : " + pipeType.D2P(dir).getName() +
                        "\n base :" + actual.getBlock() + " of :" + variablePos +
                        "\n offset :" + offset.getBlock() + " of :" + variablePos.relative(dir) +
                        "\n list :" + validPath);

                if (actual.getValue(pipeType.D2P(dir)) == pipeProperties.OUTPUT) {
                    LogUtil.info("#2 SUCCESS output found");
                    outputPos = variablePos;
                    outputSide = dir;
                    flag = false;
                    break;
                }

                if (offset.is(zBlockTag.PIPE_CONNECTORS)
                        && validPath.indexOf(variablePos.relative(dir)) == -1
                        && actual.getValue(pipeType.D2P(dir)) == pipeProperties.TRUE) {

                    LogUtil.info("#3 SUCCESS pipe found " + variablePos.relative(dir) + " at "
                            + dir + " -> "
                            + offset.getBlock());

                    validPath.add(variablePos);
                    variablePos = variablePos.relative(dir);
                    totalCheckSide = 6;
                }

                totalCheckSide--;
                LogUtil.info("#4 INFO time :" + totalCheckSide);

                if (totalCheckSide < 0) {
                    LogUtil.info("#5 FAIL no valid side found \n block:" + actual.getBlock()
                            + "\n pos:" + variablePos + "\n last dir:" + dir);
                    flag = false;
                    break;
                }
            }
        }

        if (totalCheckSide >= 0) {

            var outCap = getCap(level, outputSide.getOpposite(), outputPos);

            if (outCap == null) {
                LogUtil.info("##ERROR## output NULL");
                return;
            }

            for (int i = 0; i < inCap.getSlots(); i++) {
                if (outCap.insertItem(i, inCap.extractItem(i, 1, true), true).getCount() <= outCap
                        .getSlotLimit(i)) {
                    outCap.insertItem(i, inCap.extractItem(i, 1, false), false);
                }

            }

        }

    }

    private IItemHandler getCap(Level l, Direction d, BlockPos pos) {
        LogUtil.info("pos:" + pos + "\ndirection:" + d);

        return l != null && l.getBlockEntity(pos.relative(d)) != null
                ? Capabilities.ItemHandler.BLOCK.getCapability(l, pos.relative(d),
                        l.getBlockState(pos.relative(d)),
                        l.getBlockEntity(pos.relative(d)), d.getOpposite())
                : null;
    }

}
