package eu.hagenfaber;

import eu.hagenfaber.handlers.HighlightPacketHandler;
import eu.hagenfaber.handlers.HighlightPacketServerHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class BetterWorldOptimizationsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PayloadTypeRegistry.playS2C().register(HighlightPayload.ID, HighlightPayload.CODEC);
		ClientPlayNetworking.registerGlobalReceiver(HighlightPayload.ID, (payload, context) -> {
			BlockPos blockPos = payload.blockPos();
			context.client().execute(() -> {
				BlockState state = Blocks.STONE.getDefaultState();
				context.player().getWorld().setBlockState(blockPos, state);
			});
		});
//		ClientPlayNetworking.registerReceiver(HighlightPayload.id(), new HighlightPacketHandler());
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}