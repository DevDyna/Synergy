package com.devdyna.synergy.api;

import static com.devdyna.synergy.Main.ID;

import com.devdyna.synergy.init.Material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MultiTag {

    private TagKey<Item> item;
    private TagKey<Block> block;
    private String suffix;
    private String prefix;

    public MultiTag(String modname, String prefix, String suffix) {
        this.block = Material.tagBlock(prefix + suffix, modname);
        this.item = Material.tagItem(prefix + suffix, modname);
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public MultiTag(String modname, String full_name) {
        this(modname, full_name,"");
    }

    public MultiTag(String name) {
        this(ID, name);
    }

    public TagKey<Item> item() {
        return item;
    }

    public TagKey<Block> block() {
        return block;
    }

    public String fullName() {
        return prefix + suffix;
    }

    public String suffix() {
        return suffix;
    }

    public String prefix() {
        return prefix;
    }

}
