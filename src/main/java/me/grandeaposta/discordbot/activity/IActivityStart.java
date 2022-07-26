package me.grandeaposta.discordbot.activity;

public interface IActivityStart {
    
    void handle(ActivityStartContext actx);

    String getName();

}
