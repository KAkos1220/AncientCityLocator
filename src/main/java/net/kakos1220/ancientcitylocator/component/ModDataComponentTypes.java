package net.kakos1220.ancientcitylocator.component;

import com.mojang.serialization.Codec;
import net.kakos1220.ancientcitylocator.AncientCityLocator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DataComponentType<BlockPos> TARGET =
            register("target", builder -> builder.persistent(BlockPos.CODEC));

    public static final DataComponentType<Boolean> MESSAGE_SHOWN =
            register("message_shown", builder -> builder.persistent(Codec.BOOL));


    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(AncientCityLocator.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {}
}
