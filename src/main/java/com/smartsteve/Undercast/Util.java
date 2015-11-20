package com.smartsteve.Undercast;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

/**
 * Created by JUN on 2015-10-19.
 */
public class Util {
    public static void message(String s){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(s));
    }
    public static String getTime(int time){
        int hour = time/3600;
        int minute = (time%3600)/60;
        int second = ((time%3600)%60);
        return hour==0?minute+":"+second:hour+":"+minute+":"+second;
    }
}
