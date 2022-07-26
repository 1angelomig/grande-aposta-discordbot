package me.grandeaposta.discordbot.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import me.grandeaposta.discordbot.Config;
import me.grandeaposta.discordbot.command.CommandContext;
import me.grandeaposta.discordbot.command.ICommand;
import me.grandeaposta.discordbot.command.commands.ClearCommand;
import me.grandeaposta.discordbot.command.commands.NegaCommand;
import me.grandeaposta.discordbot.command.commands.OiCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandManager {
    
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
    
        addCommand(new OiCommand());
        addCommand(new NegaCommand());
        addCommand(new ClearCommand());
    
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = false;

        for (ICommand iCommand : this.commands) {
            if(iCommand.getName().equalsIgnoreCase(cmd.getName())){
                nameFound = true;
            }
        }

        if(nameFound){
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    @Nullable
    private ICommand getCommand(String search){
        for (ICommand iCommand : this.commands) {
            if (iCommand.getName().equals(search.toLowerCase()) || iCommand.getAliases().contains(search.toLowerCase())) {
                return iCommand;
            }
        }

        return null;
    }

    public void handle(MessageReceivedEvent event){
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "").split("\\s+");
        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if(cmd != null){
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

}
