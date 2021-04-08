package io.github.waterpicker.openworlds.mixin.client;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(SkyProperties.class)
public interface SkyPropertiesAccessor {
    @Accessor("BY_IDENTIFIER")
    Object2ObjectMap<Identifier, SkyProperties> getIdentifierMap();
}
