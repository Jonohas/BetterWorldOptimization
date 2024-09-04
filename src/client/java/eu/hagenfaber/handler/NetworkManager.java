package eu.hagenfaber.handler;

import com.mojang.brigadier.CommandDispatcher;
import eu.hagenfaber.command.BWOCommand;
import eu.hagenfaber.command.CoordsCalculate;
import net.minecraft.server.command.ServerCommandSource;

public class NetworkManager {

    /**
     * Register Handlers to the ClientPlayNetworking
     * @param handler The handler that should be registered
     */
    public void register(INetworkHandler handler) {
        handler.register();
    }

    public void registerAll() {
        this.register(new CoordsNetworkHandler());
    }
}
