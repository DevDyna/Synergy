package com.devdyna.synergy.api.recipes.builders;

@SuppressWarnings("unchecked")
public interface ConsumeInputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER consumeCatalyst();

}
