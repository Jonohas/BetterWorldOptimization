package eu.hagenfaber.handlers;

import eu.hagenfaber.HighlightPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HighlightPacketHandler implements ClientPlayNetworking.PlayPayloadHandler<HighlightPayload> {
    @Override
    public void receive(HighlightPayload payload, ClientPlayNetworking.Context context) {
        World world = context.player().getWorld();
        if (world.isClient){
            BlockPos pos = payload.blockPos();
            BlockState state = Blocks.STONE.getDefaultState();
            world.setBlockState(pos, state);
        }
    }
}
