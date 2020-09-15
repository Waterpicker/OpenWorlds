package io.github.waterpicker.openworlds.impl;

import java.util.HashMap;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

public final class DimensionInternals {
    private static final HashMap<RegistryKey<DimensionType>, DimensionType> DIMENSION_TYPES = new HashMap<>();

    private DimensionInternals() {
    }

    public static void putDimensionType(RegistryKey<DimensionType> key, DimensionType type) {
        DIMENSION_TYPES.put(key, type);
    }

    public static HashMap<RegistryKey<DimensionType>, DimensionType> getRegisteredDimensionTypes() {
        return DIMENSION_TYPES;
    }
}
