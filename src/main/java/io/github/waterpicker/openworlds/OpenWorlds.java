package io.github.waterpicker.openworlds;

import java.util.HashMap;
import java.util.Map;

import io.github.waterpicker.openworlds.mixin.client.SkyPropertiesAccessor;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;
import io.github.waterpicker.openworlds.renderer.WeatherRenderer;

import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class OpenWorlds {
    @Environment(EnvType.CLIENT)
    private static final Map<Identifier, SkyRenderer> SKY_RENDERERS = new HashMap<>();
    @Environment(EnvType.CLIENT)
    private static final Map<Identifier, CloudRenderer> CLOUD_RENDERERS = new HashMap<>();
    @Environment(EnvType.CLIENT)
    private static final Map<Identifier, WeatherRenderer> WEATHER_RENDERERS = new HashMap<>();

    /**
     * Registers a custom sky renderer for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link SkyRenderer} implementation
     */
    @Environment(EnvType.CLIENT)
    public static void registerSkyRenderer(RegistryKey<DimensionType> key, SkyRenderer renderer) {
        SKY_RENDERERS.put(key.getValue(), renderer);
    }

    /**
     * Registers a custom rain and snow renderer for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link WeatherRenderer} implementation
     */
    @Environment(EnvType.CLIENT)
    public static void registerWeatherRenderer(RegistryKey<DimensionType> key, WeatherRenderer renderer) {
        WEATHER_RENDERERS.put(key.getValue(), renderer);
    }

    /**
     * Registers a custom sky property for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param properties The Dimension Type's sky properties
     */
    @Environment(EnvType.CLIENT)
    public static void registerSkyProperty(RegistryKey<DimensionType> key, SkyProperties properties) {
        ((SkyPropertiesAccessor) properties).getIdentifierMap().put(key.getValue(), properties);
    }

    /**
     * Registers a custom cloud renderer for a Dimension Type
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link CloudRenderer} implementation
     */
    @Environment(EnvType.CLIENT)
    public static void registerCloudRenderer(RegistryKey<DimensionType> key, CloudRenderer renderer) {
        CLOUD_RENDERERS.put(key.getValue(), renderer);
    }

    @Environment(EnvType.CLIENT)
    public static SkyRenderer getSkyRenderer(Identifier key) {
        return SKY_RENDERERS.get(key);
    }

    @Environment(EnvType.CLIENT)
    public static CloudRenderer getCloudRenderer(Identifier key) {
        return CLOUD_RENDERERS.get(key);
    }

    @Environment(EnvType.CLIENT)
    public static WeatherRenderer getWeatherRenderer(Identifier key) {
        return WEATHER_RENDERERS.get(key);
    }
}
