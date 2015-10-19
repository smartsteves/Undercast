package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ModData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.DataContainer.StatsData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatParser {
	StatsData firstdata,lastdata;
	ServerData serverdata;
	ModData modData;
	boolean listen = false;
	public ChatParser(StatsData da, StatsData ld, ServerData sd, ModData modData){
		firstdata = da;
		lastdata=ld;
		serverdata = sd;
        this.modData = modData;
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
    public void onChat(ClientChatReceivedEvent e){
		boolean isFirstJoin=true;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		IChatComponent c = e.message;
		String message_unform = c.getUnformattedText();
		//  Parse Data From Homepage
		if(message_unform.contains("Welcome to the Overcast Network Lobby!")){
			serverdata.setServer("Lobby");
			modData.setOvercast(true);
			StatsData temp = WebParser.getPlayerStat(Minecraft.getMinecraft().thePlayer.getDisplayNameString());
			firstdata.setKill(temp.getKill());
			firstdata.setDeath(temp.getDeath());
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Boolean.toString(modData.isOvercast())));
			return;
		}
        if(false){
            return;
        }
    	/*
    	 * Filter useless chat
    	 */
    	if (message_unform.equals("Unknown command. Type \"/help\" for help.") || message_unform.startsWith("<") || message_unform.startsWith("[") || message_unform.startsWith("(From ") || message_unform.startsWith("(To ") || message_unform.startsWith("[Tip] ")) {
    		return;
    	}
    	/*
    	 * 
    	 * Do not Touch Over this line
    	 * 
    	 */
    	
    	 // Filter Server Change Using Portal
    	if (message_unform.equals("                         ")&&!serverdata.isLobby()&&!serverdata.getServer().equals("")) {
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/server");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    		return;
    	}
    	
    	 // Filter Server Change
    	if(message_unform.contains("Created by")){
    		serverdata.setTeam("Observer");
    		serverdata.setTeamColor("b");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    		return;
    	}    	
    	if(message_unform.startsWith("You are currently on ")){
    		serverdata.setServer(message_unform.replace("You are currently on ",""));
    		return;
    	}
    	

    	 // Filter Map Information
    	
    	if(message_unform.startsWith("---")&&!message_unform.contains("Match Info")&&!message_unform.contains("Overcast Network Servers")&&!message_unform.contains("Current Rotation")&&!message_unform.contains("Friends")){
    		String s = message_unform.replace("-", "").trim();
    		char[] tc = s.toCharArray();
    		StringBuilder sb = new StringBuilder();
    		for(int i=0;i<tc.length-2;i++){
    			sb.append(tc[i]);
    			if(tc[i]==' '&&(tc[i+2]=='.'||tc[i+3]=='.'))break;
    		}
    		serverdata.setMap(sb.toString());
    		return;
    	}
    	if(message_unform.contains("Match Info")){
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/myteam");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    		return;
    	}
    	
    	// Filter Map cycle

    	/*if(message_unform.startsWith("Match starting in 20 seconds")){
    		serverdata.setTeam("Observer");
    		serverdata.setTeamColor("b");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/myteam");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    		return;
    	}*/

    	// Filter Next Map
    	
    	if(message_unform.startsWith("Next map:")){
    		serverdata.setNextMap(message_unform.replace("Next map: ", "").split(" by")[0]);
    		player.addChatMessage(new ChatComponentText(serverdata.getNextMap()));
    		return;
    	}
    	
    	// Filter Time
    	/*if(message_unform.startsWith("Time: ")){
    		player.addChatMessage(new ChatComponentText(message_unform.replace("Time: ", "").split(".")[0]));
    		return;
    	}*/
    	
    	// Filter Team Information

    	if(message_unform.startsWith("You joined")) {
			serverdata.setTeam(message_unform.replace("You joined ", ""));
			serverdata.setTeamColor(String.valueOf(c.getFormattedText().replace("You joined ", "").toCharArray()[7]));
			return;
		}

    	 // Filter Kill

    	if ((message_unform.contains("by " + player.getDisplayNameString()) || message_unform.contains(player.getDisplayNameString()+"'s")|| message_unform.contains("took " + player.getDisplayNameString()) || message_unform.contains("fury of " + player.getDisplayNameString())) && !message_unform.toLowerCase().contains(" destroyed by ")) {
    		firstdata.addKill();
    		lastdata.addKill();	      
    	}

    	 // Filter Death

    	if (message_unform.startsWith(player.getDisplayNameString()) && !message_unform.toLowerCase().endsWith(" team")) {
    		firstdata.addDeath();
    		lastdata.addDeath();
    		return;
    	}
    	if(message_unform.startsWith("Connecting you to ")||message_unform.startsWith("Teleporting you to")){
    		serverdata.setTeam("Observer");
    		serverdata.setTeamColor("b");
    		serverdata.setServer(message_unform.substring(18,message_unform.length()));
    		return;
    	}    	
    	if(message_unform.startsWith("You are currently on ")){
    		serverdata.setServer(message_unform.replace("You are currently on ", ""));
    		return;
    	}
    }
}
