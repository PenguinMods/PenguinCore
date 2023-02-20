package com.blockypenguin.penguincore.init;

import org.slf4j.Logger;

import com.blockypenguin.penguincore.Ref;

import net.fabricmc.api.ModInitializer;

public class CommonInit implements ModInitializer {
	public static final Logger LOGGER = Ref.getInstance().createLogger("Initialisation");

	@Override
	public void onInitialize() {
		LOGGER.info("PenguinCore loading...");
	}
}