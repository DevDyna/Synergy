package com.devdyna.synergy.init;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import java.util.function.*;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.ItemComponents;
import com.devdyna.synergy.init.builder.ItemToolTipped;
import com.devdyna.synergy.init.builder.decorative.DecorativeBlock;
import com.devdyna.synergy.init.types.*;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;

@SuppressWarnings({ "null", "unchecked" })
public class Material {
        public static void register(IEventBus bus) {
                zBlockEntities.register(bus);
                zItems.register(bus);
                zBlocks.register(bus);
                zBlockTag.register(bus);
                zContainer.register(bus);
                zFluidTags.register(bus);
                // zFluidTypes.register(bus);
                zFluids.register(bus);
                zHandlers.register(bus);
                zItemTag.register(bus);
                // zProperties.register(bus);
                zComponents.register(bus);
                zCreativeTab.register(bus);
                zRecipeTypes.register(bus);

                zMachines.register(bus);
                zEntityTag.register(bus);
        }

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, Supplier<Block> sup) {
                return registerItemBlock(blockname, sup, zBlocks.zBlockItem);
        }

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         * @param b   Blocks.zBlock
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, Supplier<Block> sup,
                        DeferredRegister.Blocks b) {
                DeferredHolder<Block, Block> block = b.register(blockname, sup);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, DeferredRegister.Blocks b) {
                return registerItemBlock(blockname, () -> new Block(BlockBehaviour.Properties.of()), b);
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                x.rl(Main.ID, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                x.rl(Main.ID, name));
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name, String modname) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                x.rl(modname, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name, String modname) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                x.rl(modname, name));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String name) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                x.rl(Main.ID, name));
        }

        /**
         * create an itemtag
         */
        public static TagKey<EntityType<?>> tagEntity(String name) {
                return tagEntity(ID, name);
        }

        /**
         * create an biome tag
         */
        public static TagKey<Biome> tagBiome(String name) {
                return TagKey.create(Registries.BIOME, x.rl(Main.ID, name));
        }

        /**
         * create an entity tag
         */
        public static TagKey<EntityType<?>> tagEntity(String modname, String name) {
                return TagKey.create(Registries.ENTITY_TYPE, x.rl(modname, name));
        }

        /**
         * like rice
         */
        public static DeferredItem<ItemNameBlockItem> seedFoodItem(String name, Block b, int nutrition,
                        float saturationModifier, boolean fastToEat, boolean isAlwaysEdible) {
                var food = (new FoodProperties.Builder())
                                .nutrition(nutrition)
                                .saturationModifier(saturationModifier);

                if (fastToEat)
                        food.fast();

                if (isAlwaysEdible)
                        food.alwaysEdible();

                return zItems.zFoods.register(name,
                                () -> new ItemNameBlockItem(b, new Item.Properties().food(food.build())));
        }

        public static DeferredItem<ItemNameBlockItem> seedItem(String name, Block b) {
                return zItems.zSeeds.register(name,
                                () -> new ItemNameBlockItem(b, new Item.Properties()));
        }

        public static DeferredHolder<Block, Block> stair(DeferredHolder<Block, Block> b) {
                return registerItemBlock(b.getRegisteredName().replace(ID + ":", "") + "_stair",
                                () -> new StairBlock(b.get().defaultBlockState(),
                                                BlockBehaviour.Properties.ofFullCopy(b.get())),
                                zBlocks.zBlockStair);
        }

        public static DeferredHolder<Block, Block> slab(DeferredHolder<Block, Block> b) {
                return registerItemBlock(b.getRegisteredName().replace(ID + ":", "") + "_slab",
                                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(b.get())), zBlocks.zBlockSlab);

        }

        public static DeferredHolder<Block, Block> DecoBlock(String name, Properties prop, Blocks blockSets) {
                return registerItemBlock(name, () -> new DecorativeBlock(prop), blockSets);
        }

        /**
         * @param factory     Class::new
         * @param validBlocks BLOCK1,BLOCK2
         */
        public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> createBlockEntity(
                        String name,
                        BlockEntitySupplier<T> factory, Supplier<? extends Block>... validBlocks) {
                return zBlockEntities.zBE.register(name,
                                () -> BlockEntityType.Builder.of(factory, Arrays.stream(validBlocks)
                                                .map(Supplier::get)
                                                .toArray(Block[]::new)).build(null));
        }

        public static DeferredHolder<Item, Item> craftingItem(String name) {
                return zItems.zCraftingComponents
                                .register(name, () -> new ItemComponents());
        }

        public static DeferredHolder<Item, Item> tooltippedItem(String name,
                        DeferredRegister.Items i) {
                return i.register(name, () -> new ItemToolTipped(name + ".tip"));
        }

        public static DeferredHolder<Item, Item> resourceItem(String name) {
                return zItems.zResources
                                .registerSimpleItem(name);
        }

        public static DeferredHolder<Item, Item> machineUpgrade(String name) {
                return zItems.zMachineUpgrades
                                .registerSimpleItem(name+"_"+zStatic.MachineUpgrades.TYPE);
        }

        public static DeferredHolder<Item, Item> droplet(String name) {
                return zItems.zDropLets
                                .registerSimpleItem(name);
        }

        public static DeferredHolder<Item, Item> resourceFoil(String name) {
                return zItems.zFoils
                                .registerSimpleItem(name + zStatic.ResourceType.foil);
        }

        public static DeferredHolder<Item, Item> resourceCoil(String name) {
                return zItems.zCoils
                                .registerSimpleItem(name + zStatic.ResourceType.coil);
        }

        public static DeferredHolder<Item, Item> resourceGem(String name) {
                return zItems.zGems
                                .registerSimpleItem(name + zStatic.ResourceType.gem);
        }

        public static DeferredHolder<Item, Item> resourceIngot(String name) {
                return zItems.zIngots
                                .registerSimpleItem(name + zStatic.ResourceType.ingot);
        }

        public static DeferredHolder<Item, Item> resourceNugget(String name) {
                return zItems.zNuggets
                                .registerSimpleItem(name + zStatic.ResourceType.nugget);
        }

        public static DeferredHolder<Item, Item> resourcePlate(String name) {
                return zItems.zPlates
                                .registerSimpleItem(name + zStatic.ResourceType.plate);
        }

        public static DeferredHolder<Item, Item> resourceRaw(String name) {
                return zItems.zRawOres
                                .registerSimpleItem(zStatic.ResourceType.raw + name);
        }

        public static DeferredHolder<Item, Item> resourceShard(String name) {
                return zItems.zShards
                                .registerSimpleItem(name + zStatic.ResourceType.shard);
        }

        public static DeferredHolder<Item, Item> resourceGear(String name) {
                return zItems.zGears
                                .registerSimpleItem(name + zStatic.ResourceType.gear);
        }

        public static DeferredHolder<Item, Item> resourceDust(String name) {
                return zItems.zDusts
                                .registerSimpleItem(name + zStatic.ResourceType.dust);
        }

        public static DeferredHolder<Item, Item> resourcePellet(String name) {
                return zItems.zPellets
                                .registerSimpleItem(name + zStatic.ResourceType.pellet);
        }

        public static DeferredHolder<CreativeModeTab, CreativeModeTab> createCreativeTab(
                        String id,
                        Supplier<Item> icon) {
                return zCreativeTab.zCreative
                                .register(Main.ID+"_"+id, () -> CreativeModeTab.builder()
                                                .title(Component.translatable(
                                                                Main.ID + "." + zStatic.CreativeTab.TYPE + "." + id))
                                                .icon(() -> icon.get().getDefaultInstance())// REQUIRE SUPPLIER
                                                .build());
        }


}
