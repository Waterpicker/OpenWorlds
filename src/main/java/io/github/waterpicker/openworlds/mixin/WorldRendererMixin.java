package io.github.waterpicker.openworlds.mixin;

import io.github.waterpicker.openworlds.OpenWorlds;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;
import io.github.waterpicker.openworlds.renderer.WeatherRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow @Final private MinecraftClient client;

	@Inject(at = @At("HEAD"), method = "renderWeather", cancellable = true)
	private void renderClouds(LightmapTextureManager manager, float tickDelta, double x, double y, double z, CallbackInfo info) {
		WeatherRenderer renderer = OpenWorlds.getWeatherRenderer(client.world.getDimensionRegistryKey());

		if(renderer != null) {
			renderer.render(client, manager, tickDelta, x,y,z);
			info.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "renderClouds(Lnet/minecraft/client/util/math/MatrixStack;FDDD)V", cancellable = true)
	private void renderCloud(MatrixStack matrices, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo info) {
		CloudRenderer renderer = OpenWorlds.getCloudRenderer(client.world.getDimensionRegistryKey());

		if(renderer != null) {
			renderer.render(client, matrices, tickDelta, cameraX, cameraY, cameraZ);
			info.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V", cancellable = true)
	private void renderSky(MatrixStack matrices, float tickDelta, CallbackInfo info) {
		SkyRenderer renderer = OpenWorlds.getSkyRenderer(client.world.getDimensionRegistryKey());

		if(renderer != null) {
			renderer.render(client, matrices, tickDelta);
			info.cancel();
		}
	}
}
