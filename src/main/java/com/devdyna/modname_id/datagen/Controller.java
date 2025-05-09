package com.devdyna.modname_id.datagen;

import static com.devdyna.modname_id.Main.MODID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.modname_id.datagen.client.DataBlockModelState;
import com.devdyna.modname_id.datagen.client.DataItemModel;
import com.devdyna.modname_id.datagen.client.DataLang;
import com.devdyna.modname_id.datagen.server.DataBlockTag;
import com.devdyna.modname_id.datagen.server.DataItemTag;
import com.devdyna.modname_id.datagen.server.DataLoot;
import com.devdyna.modname_id.datagen.server.DataRecipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
public class Controller {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator g = e.getGenerator();
        PackOutput po = g.getPackOutput();
        ExistingFileHelper f = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> pr = e.getLookupProvider();

        // client

        providerGen(e, g, new DataBlockModelState(po, f));
        providerGen(e, g, new DataItemModel(po, f));
        providerGen(e, g, new DataLang(po));

        // server
        DataBlockTag blocktag = new DataBlockTag(po, pr, f);
        providerGen(e, g, blocktag);
        providerGen(e, g, new DataItemTag(po, pr, blocktag.contentsGetter()));
        providerGen(e, g, new DataLoot(po, pr));
        providerGen(e, g, new DataRecipe(po, pr));

    }

    private static <T extends DataProvider> void providerGen(GatherDataEvent e, DataGenerator g, T f) {
        g.addProvider(e.includeClient(), f);
    }

}
