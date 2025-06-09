package com.devdyna.synergy.init.types;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.core.MultiTag;

public class zMultiTags {

  public static  List<MultiTag> ALL_DEPOSITS = List.of(
            Soil(zStatic.Resources.Soils.ANDESITE),
            Soil(zStatic.Resources.Soils.BASALT),
            Soil(zStatic.Resources.Soils.BLACKSTONE),
            Soil(zStatic.Resources.Soils.BLUE_ICE),
            Soil(zStatic.Resources.Soils.CALCITE),
            Soil(zStatic.Resources.Soils.CLAY),
            Soil(zStatic.Resources.Soils.COBBLESTONE),
            Soil(zStatic.Resources.Soils.COBBLED_DEEPSLATE),
            Soil(zStatic.Resources.Soils.COARSE_DIRT),
            Soil(zStatic.Resources.Soils.DEEPSLATE),
            Soil(zStatic.Resources.Soils.DIORITE),
            Soil(zStatic.Resources.Soils.DRIPSTONE_BLOCK),
            Soil(zStatic.Resources.Soils.DIRT),
            Soil(zStatic.Resources.Soils.END_STONE),
            Soil(zStatic.Resources.Soils.GRAVEL),
            Soil(zStatic.Resources.Soils.GRANITE),
            Soil(zStatic.Resources.Soils.GILDED_BLACKSTONE),
            Soil(zStatic.Resources.Soils.ICE),
            Soil(zStatic.Resources.Soils.MAGMA_BLOCK),
            Soil(zStatic.Resources.Soils.MOSS_BLOCK),
            Soil(zStatic.Resources.Soils.MOSSY_COBBLESTONE),
            Soil(zStatic.Resources.Soils.MUD),
            Soil(zStatic.Resources.Soils.NETHERRACK),
            Soil(zStatic.Resources.Soils.OBSIDIAN),
            Soil(zStatic.Resources.Soils.PACKED_ICE),
            Soil(zStatic.Resources.Soils.PACKED_MUD),
            Soil(zStatic.Resources.Soils.PRISMARINE),
            Soil(zStatic.Resources.Soils.RED_SAND),
            Soil(zStatic.Resources.Soils.ROOTED_DIRT),
            Soil(zStatic.Resources.Soils.SCULK),
            Soil(zStatic.Resources.Soils.SOUL_SAND),
            Soil(zStatic.Resources.Soils.SOUL_SOIL),
            Soil(zStatic.Resources.Soils.SAND),
            Soil(zStatic.Resources.Soils.SANDSTONE),
            Soil(zStatic.Resources.Soils.SNOW_BLOCK),
            Soil(zStatic.Resources.Soils.STONE),
            Soil(zStatic.Resources.Soils.TUFF),
            Soil(zStatic.Resources.Soils.CRYING_OBSIDIAN),

            RawMaterial(zStatic.Resources.RawMaterials.COPPER),
            RawMaterial(zStatic.Resources.RawMaterials.IRON),
            RawMaterial(zStatic.Resources.RawMaterials.GOLD),
            RawMaterial(zStatic.Resources.RawMaterials.PLUTONIUM),
            RawMaterial(zStatic.Resources.RawMaterials.CHROMIUM),
            RawMaterial(zStatic.Resources.RawMaterials.ALUMINUM),
            RawMaterial(zStatic.Resources.RawMaterials.TIN),
            RawMaterial(zStatic.Resources.RawMaterials.NICKEL),
            RawMaterial(zStatic.Resources.RawMaterials.ZINC),
            RawMaterial(zStatic.Resources.RawMaterials.SILVER),
            RawMaterial(zStatic.Resources.RawMaterials.LEAD),
            RawMaterial(zStatic.Resources.RawMaterials.OSMIUM),
            RawMaterial(zStatic.Resources.RawMaterials.URANIUM),
            RawMaterial(zStatic.Resources.RawMaterials.IRIDIUM),
            RawMaterial(zStatic.Resources.RawMaterials.PLATINUM),

            Gem(zStatic.Resources.Gems.COAL),
            Gem(zStatic.Resources.Gems.QUARTZ),
            Gem(zStatic.Resources.Gems.AMETHYST_SHARD),
            Gem(zStatic.Resources.Gems.LAPIS_LAZULI),
            Gem(zStatic.Resources.Gems.DIAMOND),
            Gem(zStatic.Resources.Gems.EMERALD),
            Gem(zStatic.Resources.Gems.NETHERITE_SCRAP),
            Gem(zStatic.Resources.Gems.RUBY),
            Gem(zStatic.Resources.Gems.SAPPHIRE),
            Gem(zStatic.Resources.Gems.PERIDOT),
            Gem(zStatic.Resources.Gems.FLUORITE),
            Gem(zStatic.Resources.Gems.CINNABAR),

            Dust(zStatic.Resources.Dusts.GLOWSTONE),
            Dust(zStatic.Resources.Dusts.REDSTONE),
            Dust(zStatic.Resources.Dusts.SALT),
            Dust(zStatic.Resources.Dusts.SULFUR));

    public static MultiTag Soil(String name) {
        return new MultiTag("c", "soils/" , name);
    }

    public static MultiTag RawMaterial(String name) {
        return new MultiTag("c", "raw_materials/" , name);
    }

    public static MultiTag Dust(String name) {
        return new MultiTag("c", "dusts/" , name);
    }

    public static MultiTag Gem(String name) {
        return new MultiTag("c", "gems/" , name);
    }
}