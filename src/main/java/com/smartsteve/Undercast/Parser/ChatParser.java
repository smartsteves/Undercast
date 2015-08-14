package com.smartsteve.Undercast.Parser;

import static com.smartsteve.Undercast.Parser.WebParser.parseData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.smartsteve.Undercast.DataContainer.StatsData;

public class ChatParser {
	StatsData firstdata,lastdata;
	public ChatParser(StatsData da, StatsData ld){
		firstdata = da;
		lastdata=ld;
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
    public void onChat(ClientChatReceivedEvent e){
		boolean isFirstJoin=true;
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
    	IChatComponent c = e.message;
    	String message_unform = c.getUnformattedText();
    	/*
    	 *  Parse Data From Homepage
    	 */
    	if(message_unform.contains("Welcome")&&isFirstJoin){
    		isFirstJoin = !isFirstJoin;
    		StatsData temp = parseData(Minecraft.getMinecraft().thePlayer.getDisplayNameString());
    		firstdata.setKill(temp.getKill());
    		firstdata.setDeath(temp.getDeath());
    		firstdata.setKilled(temp.getKilled());
    		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Integer.toString(temp.getKill())+Integer.toString(temp.getDeath())+Integer.toString(temp.getKilled())));
    	}
    	/*
    	 * Filter useless chat
    	 */
    	if (message_unform.equals("Unknown command. Type \"/help\" for help.") || message_unform.startsWith("<") || message_unform.startsWith("[") || message_unform.startsWith("(From ") || message_unform.startsWith("(To ") || message_unform.startsWith("[Tip] ")) {
    		return;
    	}
    	/*
    	 * Filter Kill
    	 */
    	if ((message_unform.contains("by " + p.getDisplayNameString()) || message_unform.contains(p.getDisplayNameString()+"'s")|| message_unform.contains("took " + p.getDisplayNameString()) || message_unform.contains("fury of " + p.getDisplayNameString())) && !message_unform.toLowerCase().contains(" destroyed by ")) {
    		firstdata.addKill();
    		lastdata.addKill();	      
    	}
    	/*
    	 * FilterDeath
    	 */
    	if (message_unform.startsWith(p.getDisplayNameString()) && !message_unform.toLowerCase().endsWith(" team")) {
    		// If you die from someone (Killed)
    		if ((message_unform.contains(" by ") || message_unform.contains(" took ") || message_unform.contains(" fury of"))) {
    			firstdata.addKilled();
    			lastdata.addKilled();
    		}
    		// Deaths
    		else{
    			firstdata.addDeath();
    			lastdata.addDeath();
    		}
    		return;
    	}
    }
}
