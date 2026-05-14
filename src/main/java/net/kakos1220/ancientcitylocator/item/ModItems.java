package net.kakos1220.ancientcitylocator.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.kakos1220.ancientcitylocator.AncientCityLocator;
import net.kakos1220.ancientcitylocator.item.custom.AncientCompassItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class ModItems {

    public static final Item ANCIENT_COMPASS = registerItem("ancient_compass",
            properties -> new AncientCompassItem(properties.stacksTo(1).rarity(Rarity.UNCOMMON)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AncientCityLocator.MOD_ID, "ancient_compass")))
            )
    );

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(AncientCityLocator.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AncientCityLocator.MOD_ID, name)))));
    }

    public static void registerModItems() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.insertAfter(
                    new ItemStack(Items.RECOVERY_COMPASS),
                    new ItemStack(ANCIENT_COMPASS)
            );
        });
    }
}