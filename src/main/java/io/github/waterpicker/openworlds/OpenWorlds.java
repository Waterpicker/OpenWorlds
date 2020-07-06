package io.github.waterpicker.openworlds;

import io.github.waterpicker.openworlds.renderer.WeatherRenderer;
import net.fabricmc.api.ClientModInitializer;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;
import java.util.Map;

public class OpenWorlds implements ClientModInitializer {
	private static final Map<RegistryKey<DimensionType>, SkyRenderer> skyRenderers = new HashMap<>();
	private static final Map<RegistryKey<DimensionType>, CloudRenderer> cloudRenderers = new HashMap<>();
	private static final Map<RegistryKey<DimensionType>, WeatherRenderer> weatherRenderers = new HashMap<>();

	public static void registerSkyRenderer(RegistryKey<DimensionType> key, SkyRenderer renderer) {
		skyRenderers.put(key, renderer);
	}

	public static void registerWeatherRenderer(RegistryKey<DimensionType> key, WeatherRenderer renderer) {
		weatherRenderers.put(key, renderer);
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

	@Override
	public void onInitializeClient() {
	}
}
