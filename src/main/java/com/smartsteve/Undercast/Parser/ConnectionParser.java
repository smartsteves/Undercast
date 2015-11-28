package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by JUN on 2015-10-10.
 */
/*
 * This Class Parse Connection From GUI Statue.
 */
public class ConnectionParser {
    ServerData serverData;
    PingParser parser;

    public ConnectionParser(ServerData serverData) {
        this.serverData = serverData;
        //parser= new PingParser(serverData);
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiDownloadTerrain) {
            if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains(".oc.tc")) {
                serverData.setOvercast(true);
                System.out.println("true");
            } else {
                serverData.setOvercast(false);
                System.out.println("false");
            }
        }
        if (event.gui instanceof GuiDisconnected || event.gui instanceof GuiMainMenu) {
            serverData.setOvercast(false);
            System.out.println("false");
        }
    }
}
