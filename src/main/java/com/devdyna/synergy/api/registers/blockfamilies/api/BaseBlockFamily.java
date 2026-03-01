package com.devdyna.synergy.api.registers.blockfamilies.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.Material;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class BaseBlockFamily<T extends BaseBlockFamily<T>> {

    protected String id;

    protected BlockBehaviour.Properties properties = Properties.of();

    protected TagKey<Block> tagkey = null;

    protected List<DeferredHolder<Block, Block>> allBlocks = new ArrayList<>();

    public BaseBlockFamily(String id, Properties p) {
        this.id = id;
        this.properties = p;
    }

    public String getId() {
        return id;
    }

    public void buildCreativeTab(Supplier<BuildCreativeModeTabContentsEvent> s) {
        allBlocks.stream()
                .map(DeferredHolder::get)
                .map(Block::asItem)
                .map(x::item)
                .filter(Predicate.not(ItemStack::isEmpty))
                .forEach(s.get()::accept);
    }

    public Block[] getAll() {
        return allBlocks.stream().map(DeferredHolder::get).toArray(Block[]::new);
    }

    public T createTag(String id) {
        this.tagkey = Material.tagBlock(id);
        return getBuilder();
    }

    public T createTag() {
        return createTag(id + "_any");
    }

    public TagKey<Block> getTagkey() {
        return tagkey;
    }

    protected abstract T getBuilder();

}
