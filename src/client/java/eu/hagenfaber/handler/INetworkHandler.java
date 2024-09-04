package eu.hagenfaber.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public interface INetworkHandler<T extends CustomPayload> extends ClientPlayNetworking.PlayPayloadHandler<T> {
    void register();

    void receive(T payload, ClientPlayNetworking.Context context);
}
