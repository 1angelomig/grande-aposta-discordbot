package me.grandeaposta.discordbot.managers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import me.grandeaposta.discordbot.activity.ActivityStartContext;
import me.grandeaposta.discordbot.activity.IActivityStart;
import me.grandeaposta.discordbot.activity.activities.DayZ;
import me.grandeaposta.discordbot.activity.activities.LeagueOfLegends;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;

public class ActivityStartManager {

    private final List<IActivityStart> activities = new ArrayList<>();

    public ActivityStartManager() {
        addActivityStart(new LeagueOfLegends());
        addActivityStart(new DayZ());
    }

    private void addActivityStart(IActivityStart act) {
        activities.add(act);
    }

    @Nullable
    private IActivityStart getActivityStart(String search) {
        for (IActivityStart iact : this.activities) {
            if (iact.getName().equals(search)) {
                return iact;
            }
        }

        return null;
    }

    public void handle(UserActivityStartEvent event) {
        IActivityStart act = this.getActivityStart(event.getNewActivity().getName());

        if (act == null) {
            return;
        }

        if (!event.getNewActivity().getName().equals(act.getName())) {
            return;
        }
        
        ActivityStartContext actx = new ActivityStartContext(event);

        act.handle(actx);
    }

}
