package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Parser.ChatParser.English;
import com.smartsteve.Undercast.Util;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

/**
 * Created by JUN on 2015-11-22.
 */
public abstract class ChatParserBase {
    private final ServerData server;
    private boolean firstJoin = false;
    public ChatParserBase(ServerData serverData) {
        server = serverData;
    }

    public abstract void onChat(String message, String unformedMessage, String player);

    public void update() {
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/myteam");
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    }

    private void executeEnglish(String message, String unformedMessage, String player) {
        (new English(server)).onChat(message, unformedMessage, player);
    }

    protected void serverJoin() {
        if(!firstJoin){
            server.setPlaytime(0);
        }
        firstJoin = true;

        server.setServer("Lobby");
        server.setOvercast(true);
        server.setLobby(true);
    }

    protected void mapChange() {
        server.setTeam("\u00A7BObserver");
        update();
    }

    protected void serverChange(String serverName) {
        server.setTeam("\u00A7BObserver");
        server.setServer(serverName);
        server.setLobby(server.getServer().contains("Lobby"));
    }

    protected void serverCheck(String serverName) {
        server.setServer(serverName);
        server.setLobby(server.getServer().contains("Lobby"));
    }

    protected void setMap(String map) {
        server.setMap(map);
        Util.getMap(Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("eu")?"EU":"US",server.getServer().trim(), new ArrayList<String>(),map,server);
    }

    protected void setNextMap(String nextMap) {
        //server.setNextMap(nextMap);
    }

    protected void setTeam(String team) {
        server.setTeam(team);
    }

    protected void addKill() {
        server.addKill();
    }

    protected void addDeath() {
        server.addDeath();
    }

    protected void addRaindrop(int amount) {
        server.addRaindrop(amount);
    }
}
