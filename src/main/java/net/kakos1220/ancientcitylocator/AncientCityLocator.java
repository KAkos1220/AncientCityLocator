package net.kakos1220.ancientcitylocator;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.kakos1220.ancientcitylocator.component.ModDataComponentTypes;
import net.kakos1220.ancientcitylocator.event.ModEvents;
import net.kakos1220.ancientcitylocator.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AncientCityLocator implements ModInitializer {
	public static final String MOD_ID = "ancientcitylocator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModDataComponentTypes.registerDataComponentTypes();

		ModEvents.lootTableModifier();
		ModEvents.registerTooltips();
	}

	public static BlockPos ancientCityFinder(ServerLevel level, BlockPos origin) {
		Holder<Structure> ancientCity = level.registryAccess()
				.lookupOrThrow(Registries.STRUCTURE)
				.getOrThrow(BuiltinStructures.ANCIENT_CITY);

		Pair<BlockPos, Holder<Structure>> result = level.getChunkSource()
				.getGenerator()
				.findNearestMapStructure(
						level,
						HolderSet.direct(ancientCity),
						origin,
						200,
						false
				);

		return result != null ? result.getFirst() : null;
	}
}