package io.github.waterpicker.openworlds.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;


public interface SkyRenderer {
    public void render(MinecraftClient world, MatrixStack matrices, float tickDelta);
}
