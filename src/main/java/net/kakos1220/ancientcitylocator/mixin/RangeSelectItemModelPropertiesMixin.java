package net.kakos1220.ancientcitylocator.mixin;

import net.kakos1220.ancientcitylocator.AncientCityLocator;
import net.kakos1220.ancientcitylocator.client.ModCompassAngle;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RangeSelectItemModelProperties.class)
public class RangeSelectItemModelPropertiesMixin {

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerCustomProperties(CallbackInfo ci) {
        RangeSelectItemModelProperties.ID_MAPPER.put(
                Identifier.fromNamespaceAndPath(AncientCityLocator.MOD_ID, "ancient_compass"),
                ModCompassAngle.MAP_CODEC
        );
    }
}
