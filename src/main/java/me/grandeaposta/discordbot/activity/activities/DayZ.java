package me.grandeaposta.discordbot.activity.activities;

import java.io.File;

import me.grandeaposta.discordbot.Config;
import me.grandeaposta.discordbot.activity.ActivityStartContext;
import me.grandeaposta.discordbot.activity.IActivityStart;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;

public class DayZ implements IActivityStart {

    @Override
    public String getName() {
        return "DayZ";
    }

    @Override
    public void handle(ActivityStartContext actx) {

        UserActivityStartEvent event = actx.getEvent();

        event.getGuild().getTextChannelsByName(Config.get("chat_principal"), true).get(0).sendMessage(event.getUser().getAsMention()).addFile(new File("giga-chad.gif")).queue();
        
    }
    
}
