package eu.hagenfaber.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public interface BWOCommand<T> extends Command<T> {
    /**
     * @return The label of the command
     */
    void register(CommandDispatcher<T> dispatcher);
}
