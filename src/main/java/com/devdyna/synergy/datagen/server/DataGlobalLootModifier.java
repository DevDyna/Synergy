package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.concurrent.CompletableFuture;
import com.devdyna.synergy.utils.DataGenUtil;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public class DataGlobalLootModifier extends GlobalLootModifierProvider {

    public DataGlobalLootModifier(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, registries, ID);
    }

    @Override
    protected void start() {
        DataGenUtil.modifyLootTables(this, DataAnyLoot.ENTITY_DROP, "chests/jungle_temple");
    }

}
