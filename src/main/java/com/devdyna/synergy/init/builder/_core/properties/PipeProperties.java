package com.devdyna.synergy.init.builder._core.properties;

import net.minecraft.util.StringRepresentable;

public enum PipeProperties implements StringRepresentable {

    // /** denied connection by player */
    // DISABLE("disable"),
    /** no avaiable blocks to connect */
    NONE("none"),
    /** connected to other blocks */
    LINK("link");


   private final String name;

   private PipeProperties(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public String getSerializedName() {
      return this.name;
   }
}
