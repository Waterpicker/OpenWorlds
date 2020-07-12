package io.github.waterpicker.openworlds;

import io.github.waterpicker.openworlds.mixin.SkyPropertiesAccessor;
import io.github.waterpicker.openworlds.renderer.WeatherRenderer;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;

import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;
import java.util.Map;

public class OpenWorlds {
    private static final Map<RegistryKey<DimensionType>, SkyRenderer> skyRenderers = new HashMap<>();
    private static final Map<RegistryKey<DimensionType>, CloudRenderer> cloudRenderers = new HashMap<>();
    private static final Map<RegistryKey<DimensionType>, WeatherRenderer> weatherRenderers = new HashMap<>();

    public static void registerSkyRenderer(RegistryKey<DimensionType> key, SkyRenderer renderer) {
        skyRenderers.put(key, renderer); 
    }

    public static void registerWeatherRenderer(RegistryKey<DimensionType> key, WeatherRenderer renderer) {
        weatherRenderers.put(key, renderer);
    }

    public static void registerSkyProperty(RegistryKey<DimensionType> key, SkyProperties properties) {
        ((SkyPropertiesAccessor) properties).getBY_DIMENSION_TYPE().put(key, properties);
    }

    public static void registerCloudRenderer(RegistryKey<DimensionType> key, CloudRenderer renderer) {
        cloudRenderers.put(key, renderer);
    }

    public static SkyRenderer getSkyRenderer(RegistryKey<DimensionType> key) {
        return skyRenderers.get(key);
    }

    public static CloudRenderer getCloudRenderer(RegistryKey<DimensionType> key) {
        return cloudRenderers.get(key);
    }

    public static WeatherRenderer getWeatherRenderer(RegistryKey<DimensionType> key) {
        return weatherRenderers.get(key);
    }
}
