package com.blockypenguin.penguincore.init;

import org.slf4j.Logger;

import com.blockypenguin.penguincore.PenguinCoreRef;

import net.fabricmc.api.ModInitializer;

public class CommonInit implements ModInitializer {
	public static final Logger LOGGER = PenguinCoreRef.getInstance().createLogger("Initialisation");

	@Override
	public void onInitialize() {
		LOGGER.info("PenguinCore loading...");
	}
}