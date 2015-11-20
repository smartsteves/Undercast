package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Util;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by JUN on 2015-10-11.
 */
/*
 * This Class Parse Ping using command prompt
 */
public class PingParser extends Thread{
    private String server = "127.0.0.1";
    private boolean running;
    ServerData serverData;

    public PingParser(ServerData serverData){
        this.serverData = serverData;
    }
    public void startParsing(String serverName){
        server = serverName;
        this.start();
        running = true;
    }
    public void stopParsing(){
        running = false;
    }
    @Override
    public void run(){
        Process process;
        try {
            String currentServer = server;
            process = Runtime.getRuntime().exec("ping -t " + server);
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                String line;
                line = bf.readLine();
                if(line==null) continue;
                if (line.contains("=") && line.split("=").length >= 3) {
                    serverData.setPing(line.split("=")[2].split("ms")[0]);
                }
                if (!line.contains("=")) {
                    serverData.setPing("BOOM!");
                }
                if(!running||!currentServer.equals(server)){
                    break;
                }
            }
            process.destroy();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
