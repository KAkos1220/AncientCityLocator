package net.kakos1220.ancientcitylocator.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.kakos1220.ancientcitylocator.component.ModDataComponentTypes;
import net.kakos1220.ancientcitylocator.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class ModEvents {
    public static void lootTableModifier () {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key == BuiltInLootTables.ANCIENT_CITY) {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModItems.ANCIENT_COMPASS)
                                        .setWeight(1))
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                );
            }
        });
    }

    public static void registerTooltips() {
        ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> {
            BlockPos pos = stack.get(ModDataComponentTypes.TARGET);

            if (pos != null) {
                tooltip.add(Component.translatable("item.ancientcitylocator.ancient_compass.tooltip", pos.getX(), pos.getZ()));
            }
        });
    }
}
