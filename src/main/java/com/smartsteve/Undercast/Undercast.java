package com.smartsteve.Undercast;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.GUI.DisplayRatio;
import com.smartsteve.Undercast.Parser.ChatHandler;
import com.smartsteve.Undercast.Parser.ConnectionParser;
import com.smartsteve.Undercast.Parser.TimeParser;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.util.Timer;


@Mod(modid = Undercast.MODID, version = Undercast.VERSION)
public class Undercast {
    public static final String MODID = "Undercast";
    public static final String VERSION = "1.8.0.2";
    public ConnectionParser connectionParser;
    public OptionData option;
    public ServerData serverData;
    public File config;
    ChatHandler chatHandler;
    DisplayRatio displayratio;

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        option = new OptionData();
        serverData = new ServerData();
        serverData.applyWebData(Minecraft.getMinecraft().getSession().getUsername());
        System.out.println(Minecraft.getMinecraft().getSession().getUsername());
        chatHandler = new ChatHandler(serverData);
        displayratio = new DisplayRatio(option, serverData);
        ClientCommandHandler.instance.registerCommand(new CommandHandle(option, serverData));
        connectionParser = new ConnectionParser(serverData);
        MinecraftForge.EVENT_BUS.register(connectionParser);
        MinecraftForge.EVENT_BUS.register(displayratio);
        MinecraftForge.EVENT_BUS.register(chatHandler);
        Timer timer = new Timer();
        timer.schedule(new TimeParser(serverData), 1000, 1000);
    }
    /*@EventHandler
    public void preinit(FMLPreInitializationEvent event) throws Throwable{
   	 	config = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + "/UndercastSetting.data");
   	 	if(config.exists()){
   	 		//ObjectInputStream ois = new ObjectInputStream(new FileInputStream(config));
   	 		//option = (OptionData)ois.readObject();
   	 		option = new OptionData();
   	 	}
   	 	else{
   	 		config.createNewFile();
   	 		option = new OptionData();
   	 	}
    }*/

}
