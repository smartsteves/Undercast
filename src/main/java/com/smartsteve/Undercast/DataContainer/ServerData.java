package com.smartsteve.Undercast.DataContainer;

import com.smartsteve.Undercast.Parser.WebParser;

public class ServerData {
    private String server;
    private String map, nextMap;
    private String team;
    private String ping;
    private int playtime;
    private int matchtime;
    private boolean overcast, lobby;
    private int totalKill, currentKill, totalDeath, currentDeath;
    private int maxKillStreak, currentKillStreak;
    private double totalKd, currentKd;
    private int raindrop;

    public ServerData() {

    }

    public int getRaindrop() {
        return raindrop;
    }

    public void setRaindrop(int raindrop) {
        this.raindrop = raindrop;
    }

    public boolean isLobby() {
        return lobby;
    }

    public void setLobby(boolean lobby) {
        this.lobby = lobby;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(int matchtime) {
        this.matchtime = matchtime;
    }

    public int getCurrentKill() {
        return currentKill;
    }

    private void setCurrentKill(int currentKill) {
        this.currentKill = currentKill;
        update();
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    private void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
        update();
    }

    public double getTotalKd() {
        return totalKd;
    }

    public double getCurrentKd() {
        return currentKd;
    }

    public int getCurrentKillStreak() {
        return currentKillStreak;
    }

    public int getMaxKillStreak() {
        return maxKillStreak;
    }

    public int getCurrentDeath() {
        return currentDeath;
    }

    private void setCurrentDeath(int currentDeath) {
        this.currentDeath = currentDeath;
        update();
    }

    public int getTotalKill() {
        return totalKill;
    }

    private void setTotalKill(int totalKill) {
        this.totalKill = totalKill;
        update();
    }

    public boolean isOvercast() {
        return overcast;
    }

    public void setOvercast(boolean overcast) {
        this.overcast = overcast;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNextMap() {
        return nextMap;
    }

    public void setNextMap(String nextMap) {
        this.nextMap = nextMap;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public void applyWebData(String name) {
        final String nameFinal = name;
        Thread t = new Thread(new Runnable() {
            public void run() {
                int[] data = WebParser.getPlayerStat(nameFinal);
                setTotalKill(data[0]);
                setTotalDeath(data[1]);
                System.out.println(data[0] + "/" + data[1]);
            }
        });
        t.start();
    }

    public void addKill() {
        currentKillStreak++;
        currentKill++;
        totalKill++;
        if (currentKillStreak > maxKillStreak) maxKillStreak = currentKillStreak;
        update();
    }

    public void addDeath() {
        currentDeath++;
        currentKillStreak = 0;
        update();
    }

    public void addRaindrop(int raindrop) {
        this.raindrop += raindrop;
    }

    private void update() {
        if (currentDeath == 0) currentKd = currentKill;
        else currentKd = Double.valueOf(Double.valueOf(currentKill) / Double.valueOf(currentDeath));

        if (totalDeath == 0) totalKd = totalKill;
        else totalKd = Double.valueOf(Double.valueOf(totalKill) / Double.valueOf(totalDeath));
    }
    @Override
    public String toString() {
        return "Server:" + server + "   Map:" + map + "   MapNext:" + nextMap + "   Team:" + team + "   Ping:" + ping + "   MatchTime:" + matchtime + "   PlayingTime:" + playtime + "   Overcast:" + overcast + "    Lobby:" + lobby + "    CurrentKill:" + currentKill + "   CurrentDeath:" + currentDeath + "   TotalKill:" + totalKill + "   TotalDeath:" + totalDeath + "    CurrentKillStreak:" + currentKillStreak + "   MaxKillStreak:" + maxKillStreak + "   CurrentKD:" + currentKd + "   TotalKD:" + totalKd;
    }
}
