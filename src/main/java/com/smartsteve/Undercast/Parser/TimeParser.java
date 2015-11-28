package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;

import java.util.TimerTask;

/**
 * Created by JUN on 2015-11-09.
 */
public class TimeParser extends TimerTask {
    ServerData server;

    public TimeParser(ServerData server) {
        this.server = server;
    }

    public void run() {
        server.setPlaytime(server.getPlaytime() + 1);
        server.setMatchtime(server.getMatchtime() + 1);
        try {
            if(!server.isOvercast()) return;
            if (Minecraft.getMinecraft().thePlayer.getWorldScoreboard().getObjectiveNames().toArray().length >= 1&&server.getRaindrop()==0) {
                //System.out.println(String.valueOf(Minecraft.getMinecraft().thePlayer.getWorldScoreboard().getObjectiveNames().toArray()[1]).replace(",","").replace(" ",""));
                server.setRaindrop(Integer.valueOf(String.valueOf(Minecraft.getMinecraft().thePlayer.getWorldScoreboard().getObjectiveNames().toArray()[1]).replace(",", "").replace(" ","")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
