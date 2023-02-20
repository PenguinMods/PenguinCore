package com.blockypenguin.penguincore.api.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PenguinRegistryManager {
	
	private final String namespace;
	
	public PenguinRegistryManager(String namespace) {
		this.namespace = namespace;
	}
	
	public <T extends Block> T register(T object, String name) {
		return registerInto(Registry.BLOCK, object, name);
	}
	
	public <T extends Item> T register(T object, String name) {
		return registerInto(Registry.ITEM, object, name);
	}

	public <T extends BlockEntityType<?>> T register(T object, String name) {
		return registerInto(Registry.BLOCK_ENTITY_TYPE, object, name);
	}
	
	public <T extends Enchantment> T register(T object, String name) {
		return registerInto(Registry.ENCHANTMENT, object, name);
	}
	
	public <T extends Potion> T register(T object, String name) {
		return registerInto(Registry.POTION, object, name);
	}
	
	public <T extends ScreenHandlerType<?>> T register(T object, String name) {
		return registerInto(Registry.SCREEN_HANDLER, object, name);
	}
	
	public <T extends U, U> T registerInto(Registry<U> registry, T object, String name) {
		return Registry.register(registry, Identifier.of(this.namespace, name), object);
	}
}