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

public class OpenWorlds {
    private static final Map<Identifier, SkyRenderer> skyRenderers = new HashMap<>();
    private static final Map<Identifier, CloudRenderer> cloudRenderers = new HashMap<>();
    private static final Map<Identifier, WeatherRenderer> weatherRenderers = new HashMap<>();

    public static void registerSkyRenderer(Identifier key, SkyRenderer renderer) {
        skyRenderers.put(key, renderer); 
    }

    public static void registerWeatherRenderer(Identifier key, WeatherRenderer renderer) {
        weatherRenderers.put(key, renderer);
    }

    public static void registerSkyProperty(Identifier key, SkyProperties properties) {
        ((SkyPropertiesAccessor) properties).getIdentifierMap().put(key, properties);
    }

    public static void registerCloudRenderer(Identifier key, CloudRenderer renderer) {
        cloudRenderers.put(key, renderer);
    }

    public static SkyRenderer getSkyRenderer(Identifier key) {
        return skyRenderers.get(key);
    }

    public static CloudRenderer getCloudRenderer(Identifier key) {
        return cloudRenderers.get(key);
    }

    public static WeatherRenderer getWeatherRenderer(Identifier key) {
        return weatherRenderers.get(key);
    }
}
