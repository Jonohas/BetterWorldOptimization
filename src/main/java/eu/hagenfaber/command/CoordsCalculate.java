package eu.hagenfaber.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import eu.hagenfaber.BWOConstants;
import eu.hagenfaber.HighlightPayload;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;


public class CoordsCalculate implements BWOCommand<ServerCommandSource> {
    public static final Logger LOGGER = LoggerFactory.getLogger("CoordsCalculate");

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("coords").executes(this));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        Vec3d position = source.getPosition();
        ServerPlayerEntity player = source.getPlayer();
        if (player != null) {
            World world = player.getWorld();
            Identifier dimension = world.getRegistryKey().getValue();
            if (dimension.toString().equals("minecraft:overworld")) {
                if (!world.isClient)  {
                    Vec3d netherCoords = calculateNetherCoords(position);
                    ServerPlayNetworking.send(player, new HighlightPayload(new BlockPos((int) netherCoords.x, (int) netherCoords.y, (int) netherCoords.z)));
                    source.sendFeedback(() -> Text.literal("Nether coords: "+ netherCoords.x + ", " + netherCoords.y + ", " + netherCoords.z), false);
                };

            }
            else if (dimension.toString().equals("minecraft:the_nether")) {
                if (!world.isClient) {
                    Vec3d overworldCoords = calculateOverworldCoords(position);
                    ServerPlayNetworking.send(player, new HighlightPayload(new BlockPos((int) overworldCoords.x, (int) overworldCoords.y, (int) overworldCoords.z)));
                    source.sendFeedback(() -> Text.literal("Overworld coords: "+ overworldCoords.x + ", " + overworldCoords.y + ", " + overworldCoords.z), false);
                }
            }
        }
        return 1;
    }

    private static double getCoordDouble(double coord) {
        return Math.round(coord * 100) / 100.0;
    }

    private static double calculateNetherCoord(double coord) {
        return getCoordDouble(coord / 8);
    }

    private static double calculateOverworldCoord(double coord) {
        return getCoordDouble(coord * 8);
    }

    private static Vec3d calculateNetherCoords(Vec3d position) {
        return new Vec3d(calculateNetherCoord(position.x), getCoordDouble(position.y), calculateNetherCoord(position.z));
    }

    private static Vec3d calculateOverworldCoords(Vec3d position) {
        return new Vec3d(calculateOverworldCoord(position.x), getCoordDouble(position.y), calculateOverworldCoord(position.z));
    }
}
