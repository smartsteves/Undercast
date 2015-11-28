package com.smartsteve.Undercast.DataContainer;

public class OptionData {
    public boolean enable;
    public boolean fps;
    public boolean kill, death, kk, kd, killstreak;
    public boolean server, team, playingTime, matchTime, currentMap, nextMap;
    public boolean raindrop;

    public OptionData() {
        this(true, true, true, true, true, true, true, true, true, true, true, true, true, true);
    }

    public OptionData(boolean enable, boolean fps, boolean kill, boolean death, boolean kd, boolean kk, boolean killstreak, boolean server, boolean team, boolean playingTime, boolean matchTime, boolean currentMap, boolean nextMap, boolean raindrop) {
        this.enable = enable;
        this.fps = fps;
        this.kill = kill;
        this.death = death;
        this.kd = kd;
        this.kk = kk;
        this.killstreak = killstreak;
        this.server = server;
        this.team = team;
        this.playingTime = playingTime;
        this.matchTime = matchTime;
        this.currentMap = currentMap;
        this.nextMap = nextMap;
        this.raindrop = raindrop;
    }
}
