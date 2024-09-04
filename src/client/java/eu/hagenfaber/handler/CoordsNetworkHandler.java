package eu.hagenfaber.handler;

import eu.hagenfaber.HighlightPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class CoordsNetworkHandler implements INetworkHandler<HighlightPayload> {

    @Override
    public void register() {
        PayloadTypeRegistry.playS2C().register(HighlightPayload.ID, HighlightPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(HighlightPayload.ID, this);
    }

    @Override
    public void receive(HighlightPayload payload, ClientPlayNetworking.Context context) {
        BlockPos blockPos = payload.blockPos();
        context.client().execute(() -> {
            BlockState state = Blocks.STONE.getDefaultState();
            context.player().getWorld().setBlockState(blockPos, state);
        });
    }
}
