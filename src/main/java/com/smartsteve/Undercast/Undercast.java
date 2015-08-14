package com.smartsteve.Undercast;

import java.io.IOException;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.smartsteve.Undercast.DataContainer.OptionData;
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
    public OptionData option = new OptionData();
    ChatParser chatparser;
    DisplayRatio displayratio;
    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
    	chatparser = new ChatParser(firststats,laststats);
    	displayratio = new DisplayRatio(firststats,laststats,option);
    }

}
