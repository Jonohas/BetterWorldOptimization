package eu.hagenfaber;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record HighlightPayload(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<HighlightPayload> ID = new CustomPayload.Id<>(BWOConstants.HIGHLIGHT_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, HighlightPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, HighlightPayload::blockPos, HighlightPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
