package net.kakos1220.ancientcitylocator.item.custom;

import net.kakos1220.ancientcitylocator.component.ModDataComponentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import static net.kakos1220.ancientcitylocator.AncientCityLocator.*;

public class AncientCompassItem extends Item {
    public AncientCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel serverLevel = (ServerLevel) level;
        BlockPos playerPos = player.getOnPos();

        BlockPos pos = ancientCityFinder(serverLevel, playerPos);

        if (pos == null) {
            player.getItemInHand(hand).remove(ModDataComponentTypes.TARGET);
            player.sendOverlayMessage(Component.translatable("item.ancientcitylocator.ancient_compass.message.could_not_find_city"));
            return InteractionResult.SUCCESS;
        }

        double distance = Math.sqrt(playerPos.distSqr(pos));

        if (distance > 5000) {
            player.getItemInHand(hand).remove(ModDataComponentTypes.TARGET);
            player.sendOverlayMessage(Component.translatable("item.ancientcitylocator.ancient_compass.message.could_not_find_city"));
        } else {
            player.getItemInHand(hand).set(ModDataComponentTypes.TARGET, pos);
            player.sendOverlayMessage(Component.translatable("item.ancientcitylocator.ancient_compass.message.found_city", pos.getX(), pos.getZ()));
            System.out.println(pos.toShortString());
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, EquipmentSlot slot) {
        if (!(owner instanceof Player player)) return;

        if (slot == EquipmentSlot.MAINHAND && player.getMainHandItem() == itemStack) {
            if (!itemStack.has(ModDataComponentTypes.MESSAGE_SHOWN) && !itemStack.has(ModDataComponentTypes.TARGET)) {
                itemStack.set(ModDataComponentTypes.MESSAGE_SHOWN, true);
                player.sendOverlayMessage(
                        Component.translatable("item.ancientcitylocator.ancient_compass.message.no_target",
                                Component.keybind("key.use"))
                );
            }
        } else {
            itemStack.remove(ModDataComponentTypes.MESSAGE_SHOWN);
        }
    }

}
