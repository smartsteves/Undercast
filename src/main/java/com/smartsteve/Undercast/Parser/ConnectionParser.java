package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ModData;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by JUN on 2015-10-10.
 */
public class ConnectionParser {
    ModData modData;
    PingParser parser;
    public ConnectionParser(ModData modData) {
        this.modData = modData;
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event){
        /*if(Minecraft.getMinecraft().getCurrentServerData().serverIP.contains(".oc.tc")){
            modData.setOvercast(true);
            parser.start();
        }
        else{
            modData.setOvercast(false);
        }*/
        modData.setOvercast(true);
        System.out.println("abc");
    }
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event){
        modData.setOvercast(false);
        parser.stopParsing();
    }
}
