package io.github.waterpicker.openworlds.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;

public interface WeatherRenderer {
    void render(MinecraftClient client, LightmapTextureManager manager, float tickDelta, double x, double y, double z);
}
