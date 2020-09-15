package io.github.waterpicker.openworlds.impl;

import java.util.HashMap;

import com.mojang.serialization.Lifecycle;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

public final class DimensionInternals {
    private static final HashMap<RegistryKey<DimensionType>, DimensionType> DIMENSION_TYPES = new HashMap<>();
    private static final HashMap<RegistryKey<DimensionType>, Lifecycle> DIMENSION_TYPE_LIFECYCLES = new HashMap<>();

    private DimensionInternals() {
    }

    public static void putDimensionType(RegistryKey<DimensionType> key, DimensionType type, Lifecycle lifecycle) {
        DIMENSION_TYPES.put(key, type);
        DIMENSION_TYPE_LIFECYCLES.put(key, lifecycle);
    }

    public static HashMap<RegistryKey<DimensionType>, DimensionType> getRegisteredDimensionTypes() {
        return DIMENSION_TYPES;
    }

    public static HashMap<RegistryKey<DimensionType>, Lifecycle> getDimensionTypeLifecycles() {
        return DIMENSION_TYPE_LIFECYCLES;
    }
}
