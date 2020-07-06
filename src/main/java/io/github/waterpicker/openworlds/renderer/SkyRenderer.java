package io.github.waterpicker.openworlds.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public interface SkyRenderer {
    void render(MinecraftClient world, MatrixStack matrices, float tickDelta);
}
