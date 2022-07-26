package me.grandeaposta.discordbot.activity;

import net.dv8tion.jda.api.events.user.UserActivityStartEvent;

public class ActivityStartContext {

    private final UserActivityStartEvent event;

    public ActivityStartContext(UserActivityStartEvent event) {
        this.event = event;
    }

    public UserActivityStartEvent getEvent() {
        return this.event;
    }
}
