package me.grandeaposta.discordbot.command;

import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandContext {

    private final MessageReceivedEvent event;
    private final List<String> args;

    public CommandContext(MessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
    }

    public MessageReceivedEvent getEvent(){
        return this.event;
    }

    public List<String> getArgs() {
        return this.args;
    }
    
}
