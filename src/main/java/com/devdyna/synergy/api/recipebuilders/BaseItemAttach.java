package com.devdyna.synergy.api.recipebuilders;

public interface BaseItemAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

}
