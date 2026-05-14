package net.kakos1220.ancientcitylocator.client;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ModCompassAngle implements RangeSelectItemModelProperty {
    public static final MapCodec<ModCompassAngle> MAP_CODEC;
    private final ModCompassAngleState state;

    public ModCompassAngle(final boolean wobble, final ModCompassAngleState.ModCompassTarget compassTarget) {
        this(new ModCompassAngleState(wobble, compassTarget));
    }

    private ModCompassAngle(final ModCompassAngleState state) {
        this.state = state;
    }

    public float get(final ItemStack itemStack, final @Nullable ClientLevel level, final @Nullable ItemOwner owner, final int seed) {
        return this.state.get(itemStack, level, owner, seed);
    }

    public MapCodec<ModCompassAngle> type() {
        return MAP_CODEC;
    }

    static {
        MAP_CODEC = ModCompassAngleState.MAP_CODEC.xmap(ModCompassAngle::new, (c) -> c.state);
    }
}
