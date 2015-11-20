package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * This Class Parse Data From Chat. Planning using Plugin-In to localize.
 */
public class ChatParser {
	ServerData server;
	OptionData option;
	public ChatParser(ServerData server, OptionData option){
		this.option = option;
		this.server = server;
	}
	@SubscribeEvent
    public void onChat(ClientChatReceivedEvent e){
		/*if(!server.isOvercast()){
			return;
		}*/
		boolean isFirstJoin=true;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		IChatComponent c = e.message;
		String message_unform = c.getUnformattedText();

    	//filter useless chat
    	if (message_unform.equals("Unknown command. Type \"/help\" for help.") || message_unform.startsWith("<") || message_unform.startsWith("[") || message_unform.startsWith("(From ") || message_unform.startsWith("(To ") || message_unform.startsWith("[Tip] ")) {
    		return;
    	}

		//  Parse ServerJoin
		if(message_unform.contains("Welcome to the Overcast Network Lobby!")){
			server.setServer("Lobby");
			server.setOvercast(true);
			server.setLobby(true);
			return;
		}

    	 // Filter Server Change Using Portal
    	if (message_unform.equals("                         ")&&!server.isLobby()&&!server.getServer().equals("")) {
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/server");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    		return;
    	}
    	
    	 // Filter Server Change
    	if(message_unform.contains("Created by")){
    		server.setTeam("\u00A7BObserver");
    		update();
    		return;
    	}   
		
    	if(message_unform.startsWith("You are currently on ")){
    		server.setServer(message_unform.replace("You are currently on ","").replace("[","").replace("]", ""));
			System.out.println(message_unform.replace("You are currently on ","").replace("[","").replace("]",""));
			if(server.getServer().contains("Lobby")){
				server.setLobby(true);
			}
			else{
				server.setLobby(false);
			}
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
    		server.setMap(sb.toString());
    		return;
    	}
    	if(message_unform.contains("Match Info")){
    		update();
    		return;
    	}

    	// Filter Next Map
    	
    	if(message_unform.startsWith("Next map:")){
    		server.setNextMap(message_unform.replace("Next map: ", "").split(" by")[0]);
    		return;
    	}

    	// Filter Team Information
    	if(message_unform.startsWith("You joined")) {
			server.setTeam("\u00A7r"+String.valueOf(c.getFormattedText().replace("You joined ", "")));
			return;
		}

    	 // Filter Kill
    	if ((message_unform.contains("by " + player.getDisplayNameString()) || message_unform.contains(player.getDisplayNameString()+"'s")|| message_unform.contains("took " + player.getDisplayNameString()) || message_unform.contains("fury of " + player.getDisplayNameString())) && !message_unform.toLowerCase().contains(" destroyed by ")) {
    		server.addKill();
			return;
    	}

    	 // Filter Death
    	if (message_unform.startsWith(player.getDisplayNameString()) && !message_unform.toLowerCase().endsWith(" team")) {
    		server.addDeath();
    		return;
    	}
    	if(message_unform.startsWith("Connecting you to ")||message_unform.startsWith("Teleporting you to")){
    		server.setTeam("\u00A7BObserver");
    		server.setServer(message_unform.substring(19,message_unform.length()-1));
			System.out.println(message_unform.substring(19,message_unform.length()-1));
			if(server.getServer().contains("Lobby")){
				server.setLobby(true);
			}
			else{
				server.setLobby(false);
			}
    		return;
    	}
    }
	private void update(){
		Minecraft.getMinecraft().thePlayer.sendChatMessage("/myteam");
		Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
		Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
	}
}
