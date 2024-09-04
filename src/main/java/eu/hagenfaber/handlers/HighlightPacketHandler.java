package eu.hagenfaber.handlers;

import eu.hagenfaber.HighlightPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.World;

public class HighlightPacketHandler implements ServerPlayNetworking.PlayPayloadHandler<HighlightPayload> {
    @Override
    public void receive(HighlightPayload payload, ServerPlayNetworking.Context context) {
        World world = context.player().getWorld();
    }
}
