package me.grandeaposta.discordbot.command.commands;

import java.util.List;

import me.grandeaposta.discordbot.command.CommandContext;
import me.grandeaposta.discordbot.command.ICommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ClearCommand implements ICommand {

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public void handle(CommandContext ctx) {
        MessageReceivedEvent event = ctx.getEvent();

        

        List<String> args = ctx.getArgs();

        if (!isNumber(args.get(0))) {
            return;
        }

        List<Message> messageList = event.getChannel().getHistory().retrievePast(Integer.parseInt(args.get(0)) + 1).complete();
        event.getGuildChannel().deleteMessages(messageList).queue();
    }

    public boolean isNumber(String msg) {
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
