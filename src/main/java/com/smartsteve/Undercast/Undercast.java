package com.smartsteve.Undercast;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.DataContainer.StatsData;
import com.smartsteve.Undercast.GUI.DisplayRatio;
import com.smartsteve.Undercast.Parser.ChatParser;



@Mod(modid = Undercast.MODID, version = Undercast.VERSION)
public class Undercast
{
    public static final String MODID = "Undercast";
    public static final String VERSION = "1.8.0.1";  //Beta 1
    public StatsData firststats = new StatsData();
    public StatsData laststats = new StatsData();
    public OptionData option;
    public ServerData serverData = new ServerData();
    public File config;
    ChatParser chatparser;
    DisplayRatio displayratio;
    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
    	chatparser = new ChatParser(firststats,laststats,serverData);
    	displayratio = new DisplayRatio(firststats,laststats,option,serverData);
    	ClientCommandHandler.instance.registerCommand(command)
    }
    @EventHandler
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
    }

}
