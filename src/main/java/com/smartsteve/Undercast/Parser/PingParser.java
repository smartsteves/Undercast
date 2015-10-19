package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by JUN on 2015-10-11.
 */
public class PingParser extends Thread{
    boolean running = false;
    ServerData serverData;
    public PingParser(ServerData serverData){
        this.serverData = serverData;
    }
    @Override
    public void run(){
        running = true;
        Process process;
        try {
            process = Runtime.getRuntime().exec("ping -t " + String.valueOf(Minecraft.getMinecraft().getCurrentServerData().serverIP));
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while(running){
                String line;
                line = bf.readLine();
                if(line!=null&&line.contains("=")&&line.split("=").length>=3){
                    serverData.setPing(line.split("=")[2].split("ms")[0]);
                }
                if(!line.contains("=")){
                    serverData.setPing("BOOOOM!");
                }
            }
            process.destroy();
        }
        catch(Exception e){
        e.printStackTrace();
        }
    }
    public void stopParsing(){
        running = false;
    }
}
