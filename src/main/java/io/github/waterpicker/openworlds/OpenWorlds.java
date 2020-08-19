package io.github.waterpicker.openworlds;

import io.github.waterpicker.openworlds.mixin.SkyPropertiesAccessor;
import io.github.waterpicker.openworlds.renderer.WeatherRenderer;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;

import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;
import java.util.Map;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class OpenWorlds {
    private static final Map<Identifier, SkyRenderer> SKY_RENDERERS = new HashMap<>();
    private static final Map<Identifier, CloudRenderer> CLOUD_RENDERERS = new HashMap<>();
    private static final Map<Identifier, WeatherRenderer> WEATHER_RENDERERS = new HashMap<>();

    /**
     * Registers a custom sky renderer for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link SkyRenderer} implementation
     */
    public static void registerSkyRenderer(RegistryKey<DimensionType> key, SkyRenderer renderer) {
        SKY_RENDERERS.put(key.getValue(), renderer);
    }

    /**
     * Registers a custom rain and snow renderer for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link WeatherRenderer} implementation
     */
    public static void registerWeatherRenderer(RegistryKey<DimensionType> key, WeatherRenderer renderer) {
        WEATHER_RENDERERS.put(key.getValue(), renderer);
    }

    /**
     * Registers a custom sky property for a DimensionType
     *
     * @param key A RegistryKey for your Dimension Type
     * @param properties The Dimension Type's sky properties
     */
    public static void registerSkyProperty(RegistryKey<DimensionType> key, SkyProperties properties) {
        ((SkyPropertiesAccessor) properties).getIdentifierMap().put(key.getValue(), properties);
    }

    /**
     * Registers a a custom cloud renderer for a Dimension Type
     *
     * @param key A RegistryKey for your Dimension Type
     * @param renderer A {@link CloudRenderer} implementation
     */
    public static void registerCloudRenderer(RegistryKey<DimensionType> key, CloudRenderer renderer) {
        CLOUD_RENDERERS.put(key.getValue(), renderer);
    }

    public static SkyRenderer getSkyRenderer(Identifier key) {
        return SKY_RENDERERS.get(key);
    }

    public static CloudRenderer getCloudRenderer(Identifier key) {
        return CLOUD_RENDERERS.get(key);
    }

    public static WeatherRenderer getWeatherRenderer(Identifier key) {
        return WEATHER_RENDERERS.get(key);
    }
}
