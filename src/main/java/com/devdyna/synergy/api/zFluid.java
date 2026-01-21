package com.devdyna.synergy.api;

import java.util.function.Consumer;
import java.awt.Color;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zFluids;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
/**
 * Utility class to create fluids
 */
@SuppressWarnings("null")
public class zFluid {

    private int color;
    private String id;

    private DeferredHolder<Fluid, BaseFlowingFluid.Source> fluidsource;
    private DeferredHolder<Fluid, FlowingFluid> fluidflowing;
    private DeferredHolder<Item, BucketItem> itemBucket;
    private DeferredHolder<Block, LiquidBlock> block;
    private BaseFlowingFluid.Properties prop;
    private DeferredHolder<FluidType, ?> type;

    private ResourceLocation still;
    private ResourceLocation flowing;
    private ResourceLocation overlay;

    private int viscosity;
    private boolean canDrown;
    private boolean canSwim;
    private boolean canPushEntity;
    private boolean canConvertToSource;

    public zFluid(String id, float r, float g, float b, float a) {
        this(id, rgba(r, g, b, a));
    }

    public zFluid(String id, int color) {
        this.color = color;
        this.id = id;

        this.still = x.rl("minecraft", "block/water_still");
        this.flowing = x.rl("minecraft", "block/water_flow");
        this.overlay = x.rl("minecraft", "block/water_overlay");
        this.viscosity = 1000;// approx water
        this.canDrown = false;
        this.canSwim = false;
        this.canPushEntity = false;
        this.canConvertToSource = false;

        this.type = zFluids.zFluidTypes.register(
                id + "_type",
                () -> new FluidType(FluidType.Properties.create()
                        .lightLevel(10)
                        .viscosity(viscosity)
                        .canDrown(canDrown)
                        .canSwim(canSwim)
                        .canPushEntity(canPushEntity)
                        .canConvertToSource(canConvertToSource)
                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)) {

                    @SuppressWarnings({ "removal" })
                    @Override
                    public void initializeClient(Consumer<IClientFluidTypeExtensions> c) {

                        c.accept(new IClientFluidTypeExtensions() {

                            @Override
                            public ResourceLocation getStillTexture() {
                                return still;
                            }

                            @Override
                            public int getTintColor(FluidState s, BlockAndTintGetter g, BlockPos p) {
                                return color;
                            }

                            @Override
                            public ResourceLocation getFlowingTexture() {
                                return flowing;
                            }

                            @Override
                            public ResourceLocation getOverlayTexture() {
                                return overlay;
                            };

                            @Override
                            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                                return ResourceLocation.parse("textures/misc/underwater.png");
                            }

                            @Override
                            public int getTintColor() {
                                return color;
                            }

                        });
                        super.initializeClient(c);
                    }
                });

        this.prop = new BaseFlowingFluid.Properties(this.type, null, null);

        this.fluidsource = zFluids.zFluids.register(id + "_source",
                () -> new BaseFlowingFluid.Source(this.prop));

        this.fluidflowing = zFluids.zFluids.register(id + "_flowing",
                () -> new BaseFlowingFluid.Flowing(this.prop));

        this.itemBucket = zItems.zBucketItems.register(id + "_bucket",
                () -> new BucketItem(this.fluidsource.get(),
                        new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

        this.block = zBlocks.zBlockFluids.register(
                id,
                () -> new LiquidBlock(this.fluidflowing.value(),
                        BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission()
                                .strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid()
                                .sound(SoundType.EMPTY)
                                .liquid()
                                .lightLevel(value -> 10)
                                .emissiveRendering((s, g, p) -> true)));

        this.prop = new BaseFlowingFluid.Properties(
                this.type,
                this.fluidsource,
                this.fluidflowing)
                .bucket(this.itemBucket)
                .block(this.block);
    }

    public DeferredHolder<Block, LiquidBlock> getBlock() {
        return block;
    }

    public DeferredHolder<Fluid, FlowingFluid> getFlowing() {
        return fluidflowing;
    }

    public DeferredHolder<Fluid, BaseFlowingFluid.Source> getSource() {
        return fluidsource;
    }

    public DeferredHolder<Item, BucketItem> getItemBucket() {
        return itemBucket;
    }

    public ResourceLocation getStill() {
        return still;
    }

    public DeferredHolder<FluidType, ?> getType() {
        return type;
    }

    public zFluid setTextures(ResourceLocation still) {
        this.still = still;
        return this;
    }

    public zFluid setTextures(ResourceLocation still, ResourceLocation flowing) {
        this.flowing = flowing;
        return setTextures(still);
    }

    public zFluid setTextures(ResourceLocation still, ResourceLocation flowing, ResourceLocation overlay) {
        this.overlay = overlay;
        return setTextures(still, flowing);
    }

    public zFluid setStillTexture(ResourceLocation rl) {
        this.still = rl;
        return this;
    }

    public zFluid setFlowingTexture(ResourceLocation rl) {
        this.flowing = rl;
        return this;
    }

    public zFluid setOverlayTexture(ResourceLocation rl) {
        this.overlay = rl;
        return this;
    }

    public zFluid swim() {
        this.canSwim = true;
        return this;
    }

    public zFluid convertToSource() {
        this.canConvertToSource = true;
        return this;
    }

    public zFluid drown() {
        this.canDrown = true;
        return this;
    }

    public zFluid pushEntity() {
        this.canPushEntity = true;
        return this;
    }

    /**
     * Default value: 1000
     */
    public zFluid setViscosity(int v) {
        this.viscosity = v;
        return this;
    }

    public int getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public static zFluid create(String id, int color) {
        return new zFluid(id, color);
    }

    public static zFluid create(String id, Color color) {
        return new zFluid(id, color.getRGB());
    }

    public static zFluid create(String id, float r, float g, float b, float a) {
        return new zFluid(id, r, g, b, a);
    }

public static int rgba(float r, float g, float b, float a) {
    return ((int)(a * 255) << 24) 
         | ((int)(b * 255) << 16)
         | ((int)(g * 255) << 8)
         | ((int)(r * 255));
}


    public Fluid getFluid(){
        return getSource().get();
    }

}