package me.grandeaposta.discordbot.command.commands;

import me.grandeaposta.discordbot.command.CommandContext;
import me.grandeaposta.discordbot.command.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class OiCommand implements ICommand {

    @Override
    public String getName() {
        return "oi";
    }

    @Override
    public void handle(CommandContext ctx) {
        MessageReceivedEvent event = ctx.getEvent();

        event.getChannel().sendMessage(event.getAuthor().getAsMention() +" Olá").queue();
    }
    
}
