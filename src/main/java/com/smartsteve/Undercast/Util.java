package com.smartsteve.Undercast;

import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by JUN on 2015-10-19.
 */
public class Util {
    public static void message(String s) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(s));
    }

    public static String getTime(int time) {
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int second = ((time % 3600) % 60);
        return hour == 0 ? minute + ":" + second : hour + ":" + minute + ":" + second;
    }
    public static void getMap(String serverLoc, String serverGame, ArrayList<String> maps, final String currentMap, final ServerData server){
        if(serverLoc.equals("eu")) serverLoc = "EU";
        if(serverLoc.equals("us")) serverLoc = "US";
        final String fServerLoc = serverLoc;
        final String fServerGame = serverGame.replace(" ", "%20");
        final ArrayList<String> fMaps = maps;
        Thread t = new Thread(){
            @Override
            public void run(){
                int mapIndex;
                URL url;
                InputStream is;
                InputStreamReader isr;
                BufferedReader br;
                try {
                    url = new URL("https://raw.githubusercontent.com/OvercastNetwork/Rotations/master/" + fServerLoc + "/" + fServerGame);
                    is = url.openConnection().getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    fMaps.clear();
                    String line;
                    while((line=br.readLine())!=null){
                        fMaps.add(line);
                    }
                    is.close();
                    isr.close();
                    br.close();
                }
                catch(Exception e){
                    fMaps.clear();
                }
                server.setNextMap(fMaps.get(fMaps.indexOf(currentMap)==fMaps.size()?1:fMaps.indexOf(currentMap)+1));
                System.out.println(fMaps.get(fMaps.indexOf(currentMap)));
                System.out.println(fMaps.get(fMaps.indexOf(currentMap) == fMaps.size() ? 1 : fMaps.indexOf(currentMap)+1));
            }
        };
        t.start();
    }
}
