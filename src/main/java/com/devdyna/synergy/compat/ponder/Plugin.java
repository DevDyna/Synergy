// package com.devdyna.synergy.compat.ponder;

// @SuppressWarnings("null")
// public class Plugin implements PonderPlugin {

//     @Override
//     public String getModId() {
//         return ID;
//     }

//     @Override
//     public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> h) {

//         h.addStoryBoard(zBlocks.HARVESTER.getId(), x.rl("base"), StoryBoards::harvester);

//         // node
//         // redstone repeaters
//         // sprinker
//         // harvester

//     }

//     @Override
//     public void registerTags(PonderTagRegistrationHelper<ResourceLocation> h) {
//         ResourceLocation tag = x.rl("key");
//         TagBuilder builder = h.registerTag(tag);
//         builder.item(zBlocks.HARVESTER.get().asItem(), true, false);
//         h.addTagToComponent(zBlocks.HARVESTER.getId(), tag);
//         builder.addToIndex();
//         builder.register();
//     }

// }
