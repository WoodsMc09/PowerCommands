package me.woodsmc.powercommands.command;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomCommandExecuteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private CommandSender sender;
    private String command;
    private String[] args;
    private boolean cancelled;

    public CustomCommandExecuteEvent(CommandSender sender, String command, String[] args){
        this.sender = sender;
        this.command = command;
        this.args = args;
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
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
