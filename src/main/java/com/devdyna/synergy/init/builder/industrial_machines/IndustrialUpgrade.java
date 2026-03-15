package com.devdyna.synergy.init.builder.industrial_machines;

import static com.devdyna.synergy.Main.ID;

import java.util.*;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.UpgradeType;
import com.devdyna.synergy.init.types.zComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

@SuppressWarnings("null")
public class IndustrialUpgrade extends Item {

    public IndustrialUpgrade(Properties properties) {
        super(properties);
    }

    public IndustrialUpgrade() {
        this(new Item.Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext c) {
        var level = c.getLevel();
        var pos = c.getClickedPos();
        var item = c.getItemInHand();
        var be = level.getBlockEntity(pos);
        var player = c.getPlayer();

        if (player.isCrouching() && be instanceof BaseMachineBE machineBE) {

            if (machineBE.tryAddUpgrade(item)) {
                if (!player.isCreative())
                    item.shrink(1);
                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.FAIL;

    }

    /**
     * Value 0 will exclude the modifier
     * <br/>
     * <br/>
     * 100 => x 1.0
     * <br/>
     * <br/>
     * 
     * @param s  speed %
     * @param ef energy usage %
     * @param l  secondary output luck %
     * @param f  fluid usage %
     * @param ec energy capacity %
     */
    public ItemStack set(int s, int e, int l, int f) {
        return UpgradeComponents.create(this, s, e, l, f);
    }

    public record UpgradeComponents(
            Optional<Integer> speed,
            Optional<Integer> energy,
            Optional<Integer> luck,
            Optional<Integer> fluid_usage) {
        public static final Codec<UpgradeComponents> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.INT.optionalFieldOf("speed").forGetter(UpgradeComponents::speed),
                Codec.INT.optionalFieldOf("energy").forGetter(UpgradeComponents::energy),
                Codec.INT.optionalFieldOf("secondary_luck").forGetter(UpgradeComponents::luck),
                Codec.INT.optionalFieldOf("fluid_usage").forGetter(UpgradeComponents::fluid_usage))
                .apply(i, UpgradeComponents::new));

        public static final StreamCodec<FriendlyByteBuf, UpgradeComponents> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::speed,
                ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::energy,
                ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::luck,
                ByteBufCodecs.optional(ByteBufCodecs.INT), UpgradeComponents::fluid_usage,
                UpgradeComponents::new);

        public static final UpgradeComponents EMPTY = new UpgradeComponents(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());

        public static final boolean isEmpty(UpgradeComponents c) {
            return c.speed().isEmpty() &&
                    c.energy().isEmpty() &&
                    c.luck().isEmpty() &&
                    c.fluid_usage().isEmpty();
        }

        public static final boolean has(UpgradeComponents c, UpgradeType type) {
            return c == null ? false : !getAll(c).get(type.value()).isEmpty();
        }

        public static final int get(UpgradeComponents c, UpgradeType type) {
            return c == null ? 0 : getAll(c).get(type.value()).get();
        }

        public static final boolean has(ItemStack i, UpgradeType type) {
            return has(i.get(zComponents.UPGRADE_COMPONENTS), type);
        }

        public static final int get(ItemStack i, UpgradeType type) {
            return get(i.get(zComponents.UPGRADE_COMPONENTS), type);
        }

        public static final int getStacked(ItemStack i, UpgradeType type) {
            var tot = 0;
            for (int j = 0; j < i.getCount(); j++)
                tot += get(i.get(zComponents.UPGRADE_COMPONENTS), type);
            return tot;
        }

        public static final List<Optional<Integer>> getAll(UpgradeComponents c) {
            return List.of(c.speed(), c.energy(), c.luck(), c.fluid_usage());
        }

        /**
         * Value 0 will set Optional.empty()
         */
        public static final UpgradeComponents builder(int speed, int energy, int luck, int fluid) {
            return new UpgradeComponents(
                    speed == 0 ? Optional.empty() : Optional.of(speed),
                    energy == 0 ? Optional.empty() : Optional.of(energy),
                    luck == 0 ? Optional.empty() : Optional.of(luck),
                    fluid == 0 ? Optional.empty() : Optional.of(fluid));
        }

        /**
         * Value 0 will set Optional.empty()
         */
        public static ItemStack create(Item i, int speed, int energy, int luck, int fluid) {
            var item = x.item(i);
            item.set(zComponents.UPGRADE_COMPONENTS, builder(speed, energy, luck, fluid));
            return item;
        }

        public enum UpgradeType {

            SPEED(0),
            ENERGY(1),
            LUCK(2),
            FLUID(3);

            private int i;

            UpgradeType(int i) {
                this.i = i;
            }

            public int value() {
                return i;
            }
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        var nbt = stack.get(zComponents.UPGRADE_COMPONENTS);

        if (nbt != null && !UpgradeComponents.isEmpty(nbt)) {
            t.add(Component.translatable(ID + ".upgrades.title"));

            if (UpgradeComponents.has(nbt, UpgradeType.ENERGY)) {
                var energy = UpgradeComponents.get(nbt, UpgradeType.ENERGY);
                t.add(Component.translatable(ID + ".upgrades.modifier.energy",
                        ((energy < 0 ? "§a" : "§c+") + energy + "%")));
            }
            // if (UpgradeComponents.has(nbt, UpgradeType.ENERGY_CAPACITY)) {
            //     var energy_cap = UpgradeComponents.get(nbt, UpgradeType.ENERGY_CAPACITY);
            //     t.add(Component.translatable(ID + ".upgrades.modifier.energy.capacity",
            //             ((energy_cap < 0 ? "§c" : "§a+") + energy_cap + "%")));
            // }
            if (UpgradeComponents.has(nbt, UpgradeType.SPEED)) {
                var speed = UpgradeComponents.get(nbt, UpgradeType.SPEED);
                t.add(Component.translatable(ID + ".upgrades.modifier.speed",
                        ((speed >= 0 ? "§a+" : "§c") + speed + "%")));
            }
            if (UpgradeComponents.has(nbt, UpgradeType.LUCK)) {
                var luck = UpgradeComponents.get(nbt, UpgradeType.LUCK);
                t.add(Component.translatable(ID + ".upgrades.modifier.luck",
                        ((luck > 0 ? "§a+" : "§c") + luck + "%")));
            }
            if (UpgradeComponents.has(nbt, UpgradeType.FLUID)) {
                var fluid = UpgradeComponents.get(nbt, UpgradeType.FLUID);
                t.add(Component.translatable(ID + ".upgrades.modifier.fluid",
                        ((fluid < 0 ? "§a" : "§c+") + fluid + "%")));
            }

        }

    }

}
