package eu.hagenfaber.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class CommandManager {
    private final HashSet<BWOCommand> commands;
    public static final Logger LOGGER = LoggerFactory.getLogger("CommandManager");

    public CommandManager() {
        this.commands = new HashSet<>();
    }

    /**
     * Register commands to the CommandDispatcher of the server
     * @param dispatcher The original instance coming from the server
     */
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LOGGER.info("Registering commands");
        for (BWOCommand command : this.commands) {
            command.register(dispatcher);
        }
    }

    public void registerAll() {
        this.register(new CoordsCalculate());
    }

    private void register(final BWOCommand command) {
        this.commands.add(command);
    }
}
