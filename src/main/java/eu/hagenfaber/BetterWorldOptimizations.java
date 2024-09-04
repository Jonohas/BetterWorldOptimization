package eu.hagenfaber;

import eu.hagenfaber.command.CommandManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterWorldOptimizations implements ModInitializer {
	public static final String MOD_ID = "betterworldoptimizations";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public final CommandManager commandManager = new CommandManager();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		commandManager.registerAll();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> commandManager.register(dispatcher));

		LOGGER.info("Hello Fabric world!");
	}
}