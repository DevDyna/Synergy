package com.devdyna.synergy.compat.ponder;

import static com.devdyna.synergy.Main.ID;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("null")
public class Plugin implements PonderPlugin {

    @Override
    public String getModId() {
        return ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> h) {


        // h.addStoryBoard(zBlocks.HARVESTER.getId(), x.rl(""), null);

        //node
        //redstone repeaters
        //sprinker
        //harvester

    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> h) {

    }

}
