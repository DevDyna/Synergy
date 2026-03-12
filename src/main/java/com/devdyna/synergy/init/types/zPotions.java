package com.devdyna.synergy.init.types;

import com.devdyna.synergy.Main;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zPotions {
    public static void register(IEventBus bus) {

        zPotion.register(bus);

    }

    public static final DeferredRegister<Potion> zPotion = DeferredRegister
            .create(BuiltInRegistries.POTION, Main.ID);

    public static final DeferredHolder<Potion, Potion> WITHERING = zPotion.register("withering",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1600)));

    public static final DeferredHolder<Potion, Potion> HUNGER = zPotion.register("hunger",
            () -> new Potion(new MobEffectInstance(MobEffects.HUNGER, 600, 3)));

    public static final DeferredHolder<Potion, Potion> CONDUIT = zPotion.register("conduit_power",
            () -> new Potion(new MobEffectInstance(MobEffects.CONDUIT_POWER, 2400)));

}
