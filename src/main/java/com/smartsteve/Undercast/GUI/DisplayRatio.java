package com.smartsteve.Undercast.GUI;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * This Class Indicate Ratio on Screen
 */
public class DisplayRatio {
	OptionData option;
	ServerData server;
	public DisplayRatio(OptionData optionData, ServerData server){
		option = optionData;
		this.server = server;
	}
	/*
	 * FPS      *
	 * Server   *
	 * Team     *
	 * Playing Time
	 * Match Time
	 * CurrentMap  *
	 * NextMap     *
	 * K/D         *
	 * K/K         Removed
	 * Kills       *
	 * Deaths      *
	 * Raindrops
	 * Current KillStreak    *
	 */
	 @SubscribeEvent
	    public void displayRatio(RenderGameOverlayEvent.Post e){
		 Minecraft mc = Minecraft.getMinecraft();
		 if(server==null)return;
		 if (option.enable&&mc.inGameHasFocus && !mc.gameSettings.showDebugInfo&&e.type==RenderGameOverlayEvent.ElementType.HOTBAR) {
			 int x = 2, y = 2;
			 
			 if (option.fps){
				 mc.fontRendererObj.drawStringWithShadow("FPS: "+Minecraft.getDebugFPS(), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.server){
				 mc.fontRendererObj.drawStringWithShadow("Server: \u00A76"+ server.getServer(),x,y,16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.team&&!server.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("Team: "+server.getTeam(), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.playingTime){
	    			
			 }
			 if(option.matchTime &&!server.isLobby()){
				 
			 }
			 if(option.currentMap&&!server.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("Current Map: \u00A7d" + server.getMap(),x,y,16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.nextMap&&!server.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Next Map: \u00A7d" + server.getNextMap(),x,y,16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.kill&&!server.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Kill: \u00A7a" + Integer.toString(server.getCurrentKill()) + "/" + Integer.toString(server.getTotalKill()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
		  		}
			 if(option.death&&!server.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Death: \u00A74" + Integer.toString(server.getCurrentDeath())+ "/" + Integer.toString(server.getTotalDeath()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.kd&&!server.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("KD: \u00A73" + String.format("%.3f", (float)server.getCurrentKd()) + "/" + String.format("%.3f", (float)server.getTotalKd()), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.killstreak&&!server.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("KS: \u00A75" + Integer.toString(server.getCurrentKillStreak())+"/"+Integer.toString(server.getMaxKillStreak()), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 mc.fontRendererObj.drawStringWithShadow("Lobby: " + server.isLobby(),x,y,16777215);
             //mc.fontRendererObj.drawStringWithShadow("Ping: "+server.getPing(),x,y,16777215);
		 }
	 }
}
