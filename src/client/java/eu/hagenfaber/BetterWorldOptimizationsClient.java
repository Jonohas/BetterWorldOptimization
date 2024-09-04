package eu.hagenfaber;

import eu.hagenfaber.handler.NetworkManager;
import net.fabricmc.api.ClientModInitializer;

public class BetterWorldOptimizationsClient implements ClientModInitializer {

	NetworkManager networkManager = new NetworkManager();

	@Override
	public void onInitializeClient() {
		networkManager.registerAll();
	}
}