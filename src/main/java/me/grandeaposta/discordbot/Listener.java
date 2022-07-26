package me.grandeaposta.discordbot;

import me.grandeaposta.discordbot.managers.ActivityStartManager;
import me.grandeaposta.discordbot.managers.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    private final CommandManager comManager = new CommandManager();
    private final ActivityStartManager actManager = new ActivityStartManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();

        if(msg.startsWith(Config.get("prefix"))){
            comManager.handle(event);
        }
        
    }

    @Override
    public void onUserActivityStart(UserActivityStartEvent event) {
        actManager.handle(event);
    }

}
