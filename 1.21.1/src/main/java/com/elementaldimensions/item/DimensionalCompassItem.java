package com.elementaldimensions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DimensionalCompassItem extends Item {
	
	public DimensionalCompassItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		
		if (!world.isClient()) {
			String dimensionId = world.getRegistryKey().getValue().toString();
			
			// Display dimension information
			user.sendMessage(Text.literal("§6Current Dimension: §e" + getDimensionName(dimensionId)), true);
			
			// Check if player is in the Overworld
			if (dimensionId.equals("minecraft:overworld")) {
				user.sendMessage(Text.literal("§7Use this compass in dimensional realms to find portals"), false);
			} else {
				// In a dimension - provide basic guidance
				user.sendMessage(Text.literal("§aCompass active! §7Portal detection: In development"), false);
			}
		}
		
		return TypedActionResult.success(itemStack);
	}
	
	private String getDimensionName(String dimensionId) {
		return switch (dimensionId) {
			case "elementaldimensions:firelands" -> "Firelands";
			case "elementaldimensions:aquatica" -> "Aquatica";
			case "elementaldimensions:terra_depths" -> "Terra Depths";
			case "elementaldimensions:skyreach_peaks" -> "Skyreach Peaks";
			case "elementaldimensions:celestial" -> "Celestial";
			case "elementaldimensions:void_realm" -> "Void Realm";
			case "minecraft:overworld" -> "Overworld";
			case "minecraft:the_nether" -> "Nether";
			case "minecraft:the_end" -> "The End";
			default -> "Unknown";
		};
	}
}
