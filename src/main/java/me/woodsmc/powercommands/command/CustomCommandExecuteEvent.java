package me.woodsmc.powercommands.command;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomCommandExecuteEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final CommandSender sender;
    private final String command;
    private final String[] args;
    private boolean cancelled;

    public CustomCommandExecuteEvent(CommandSender sender, String command, String[] args) {
        this.sender = sender;
        this.command = command;
        this.args = args;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }
}