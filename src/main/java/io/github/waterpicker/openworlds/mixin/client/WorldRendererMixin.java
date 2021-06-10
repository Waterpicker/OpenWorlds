package io.github.waterpicker.openworlds.mixin.client;

import io.github.waterpicker.openworlds.OpenWorlds;
import io.github.waterpicker.openworlds.renderer.CloudRenderer;
import io.github.waterpicker.openworlds.renderer.SkyRenderer;
import io.github.waterpicker.openworlds.renderer.WeatherRenderer;

import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private ClientWorld world;

    @Inject(at = @At("HEAD"), method = "renderWeather", cancellable = true)
    private void renderWeather(LightmapTextureManager manager, float tickDelta, double x, double y, double z, CallbackInfo info) {
        WeatherRenderer renderer = null;
        if (this.client.world != null) {
            renderer = OpenWorlds.getWeatherRenderer(world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(world.getDimension()));
        }

        if(renderer != null) {
            renderer.render(this.client, manager, tickDelta, x,y,z);
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FDDD)V", cancellable = true)
    private void renderCloud(MatrixStack matrices, Matrix4f matrix4f, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo info) {
        CloudRenderer renderer = null;
        if (this.client.world != null) {
            renderer = OpenWorlds.getCloudRenderer(world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(world.getDimension()));
        }

        if(renderer != null) {
            renderer.render(this.client, matrices, matrix4f ,tickDelta, cameraX, cameraY, cameraZ);
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderSky", cancellable = true)
    private void renderSky(MatrixStack matrices, Matrix4f matrix4f, float tickDelta, Runnable runnable, CallbackInfo ci) {
        SkyRenderer renderer = null;
        if (this.client.world != null) {
            renderer = OpenWorlds.getSkyRenderer(world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(world.getDimension()));
        }

        if(renderer != null) {
            renderer.render(this.client, matrices, tickDelta);
            ci.cancel();
        }
    }
}
