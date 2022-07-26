package me.grandeaposta.discordbot.activity.activities;

import me.grandeaposta.discordbot.Config;
import me.grandeaposta.discordbot.activity.ActivityStartContext;
import me.grandeaposta.discordbot.activity.IActivityStart;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;

public class LeagueOfLegends implements IActivityStart {

    @Override
    public String getName() {
        return "League of Legends";
    }

    @Override
    public void handle(ActivityStartContext actx) {

        UserActivityStartEvent event = actx.getEvent();

        event.getGuild().getTextChannelsByName(Config.get("lol_ban_chat"), true).get(0)
                .sendMessage(event.getUser().getAsMention()
                        + "Aviso. Se continuar jogando LOL por mais 15 minutos vai tomar kick.")
                .queue();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (event.getMember().getActivities().size() == 0 || event.getMember().getActivities().get(0)
                                .getName() != event.getNewActivity().getName()) {
                            cancel();
                            return;

                        }

                        event.getGuild().getTextChannelsByName(Config.get("lol_ban_chat"), true).get(0)
                                .sendMessage(event.getUser().getAsMention() + " foi avisado...").queue();

                        event.getMember().kick().queue();
                    }
                },
                900000);

    }

}
