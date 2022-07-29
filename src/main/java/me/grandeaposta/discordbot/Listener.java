package me.grandeaposta.discordbot;

import me.grandeaposta.discordbot.managers.ActivityStartManager;
import me.grandeaposta.discordbot.managers.CommandManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    private final CommandManager comManager = new CommandManager();
    private final ActivityStartManager actManager = new ActivityStartManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();

        if (msg.startsWith(Config.get("prefix"))) {
            comManager.handle(event);
        }

    }

    @Override
    public void onUserActivityStart(UserActivityStartEvent event) {
        actManager.handle(event);
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        
        Member bot = event.getGuild().getSelfMember();

        if (event.getMember().getUser().isBot()) {
            return;
        }

        if (bot.getVoiceState().inAudioChannel()) {
            return;
        }

        event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if (event.getMember().getUser().isBot()) {
            return;
        }

        if(event.getChannelLeft().getMembers().size() == 0){
            return;
        }

        if (event.getChannelLeft().getMembers().size() == 1 && event.getChannelLeft().getMembers().get(0) == event.getGuild().getSelfMember()) {
            event.getGuild().getAudioManager().closeAudioConnection();
        }

    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        if (event.getMember().getUser().isBot()) {
            return;
        }

        if (event.getChannelLeft().getMembers().size() == 1 && event.getChannelLeft().getMembers().get(0) == event.getGuild().getSelfMember()) {
            event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
        }
    }
    

}
