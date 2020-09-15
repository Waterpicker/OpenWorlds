package io.github.waterpicker.openworlds.mixin;

import java.util.HashMap;

import io.github.waterpicker.openworlds.impl.DimensionInternals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import com.mojang.serialization.Lifecycle;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
    @Inject(at = @At("RETURN"), method = "addRegistryDefaults", locals = LocalCapture.PRINT)
    private static void addMoreRegistryDefaults(DynamicRegistryManager.Impl registryManager, CallbackInfoReturnable<DynamicRegistryManager.Impl> cir, MutableRegistry<DimensionType> mutableRegistry) {
        HashMap<RegistryKey<DimensionType>, DimensionType> dimensionTypes = DimensionInternals.getRegisteredDimensionTypes();
        for (RegistryKey<DimensionType> key : dimensionTypes.keySet()) {
            mutableRegistry.add(key, dimensionTypes.get(key), Lifecycle.stable());
        }
    }
}
