package com.nikhil.elementaldimensions.fluid;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registry for all custom fluids in Elemental Dimensions
 */
public class ModFluids {

    // Lucid Water (Dreaming Depths)
    public static FlowableFluid STILL_LUCID_WATER;
    public static FlowableFluid FLOWING_LUCID_WATER;

    // Radiant Fluid (Radiant Plateau)
    public static FlowableFluid STILL_RADIANT_FLUID;
    public static FlowableFluid FLOWING_RADIANT_FLUID;

    // Mycelial Ooze (Mycelial Nexus)
    public static FlowableFluid STILL_MYCELIAL_OOZE;
    public static FlowableFluid FLOWING_MYCELIAL_OOZE;

    // Void Essence (Astral Frontier)
    public static FlowableFluid STILL_VOID_ESSENCE;
    public static FlowableFluid FLOWING_VOID_ESSENCE;

    // Ink Fluid (Glyphic Archives)
    public static FlowableFluid STILL_INK_FLUID;
    public static FlowableFluid FLOWING_INK_FLUID;

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering fluids for Elemental Dimensions");

        // Lucid Water
        STILL_LUCID_WATER = register("still_lucid_water", new LucidWaterFluid.Still());
        FLOWING_LUCID_WATER = register("flowing_lucid_water", new LucidWaterFluid.Flowing());

        // Radiant Fluid
        STILL_RADIANT_FLUID = register("still_radiant_fluid", new RadiantFluid.Still());
        FLOWING_RADIANT_FLUID = register("flowing_radiant_fluid", new RadiantFluid.Flowing());

        // Mycelial Ooze
        STILL_MYCELIAL_OOZE = register("still_mycelial_ooze", new MycelialOozeFluid.Still());
        FLOWING_MYCELIAL_OOZE = register("flowing_mycelial_ooze", new MycelialOozeFluid.Flowing());

        // Void Essence
        STILL_VOID_ESSENCE = register("still_void_essence", new VoidEssenceFluid.Still());
        FLOWING_VOID_ESSENCE = register("flowing_void_essence", new VoidEssenceFluid.Flowing());

        // Ink Fluid
        STILL_INK_FLUID = register("still_ink_fluid", new InkFluid.Still());
        FLOWING_INK_FLUID = register("flowing_ink_fluid", new InkFluid.Flowing());
    }

    private static FlowableFluid register(String name, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID,
            Identifier.of(ElementalDimensions.MOD_ID, name), fluid);
    }
}
