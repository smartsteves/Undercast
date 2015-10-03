package com.smartsteve.Undercast.GUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.DataContainer.StatsData;

public class DisplayRatio {
	StatsData firststats,laststats;
	OptionData option;
	ServerData serverData;
	public DisplayRatio(StatsData first, StatsData last, OptionData op, ServerData d){
		MinecraftForge.EVENT_BUS.register(this);
		firststats = first;
		laststats = last;
		option = op;
		serverData = d;
	}
	/*
	 * FPS
	 * Server
	 * Team
	 * Playing Time
	 * Match Time
	 * CurrentMap
	 * NextMap
	 * K/D
	 * K/K
	 * Kills
	 * Deaths
	 * Raindrops
	 * Current KillStreak
	 */
	 @SubscribeEvent
	    public void displayRatio(RenderGameOverlayEvent.Post e){
		 Minecraft mc = Minecraft.getMinecraft();
		 if(firststats==null) return;
		 if (option.enable&&mc.inGameHasFocus && !mc.gameSettings.showDebugInfo&&e.type==RenderGameOverlayEvent.ElementType.HOTBAR) {
			 int x = 2, y = 2;
			 
			 if (option.fps){
				 mc.fontRendererObj.drawStringWithShadow("FPS: "+mc.debug.split(",")[0].split(" fps")[0], x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.server){
				 mc.fontRendererObj.drawStringWithShadow("Server: \u00A76"+ serverData.getServer(),x,y,16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.team&&!serverData.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("Team: \u00A7" + serverData.getTeamColor() + serverData.getTeam(), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.playingTime){
	    			
			 }
			 if(option.matchTime &&!serverData.isLobby()){
				 
			 }
			 if(option.currentMap&&!serverData.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("Current Map: \u00A7d" + serverData.getCurrentMap(),x,y,16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.nextMap&&!serverData.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Next Map: \u00A7d" + serverData.getNextMap(),x,y,16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.kill&&!serverData.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Kill: \u00A7a" + Integer.toString(laststats.getKill()) + "/" + Integer.toString(firststats.getKill()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
		  		}
			 if(option.death&&!serverData.isLobby()){
	    			mc.fontRendererObj.drawStringWithShadow("Death: \u00A74" + Integer.toString(laststats.getDeath())+ "/" + Integer.toString(firststats.getDeath()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.kd&&!serverData.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("KD: \u00A73" + String.format("%.3f", (float)laststats.getKd()) + "/" + String.format("%.3f", (float)firststats.getKd()), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
			 if(option.killstreak&&!serverData.isLobby()){
				 mc.fontRendererObj.drawStringWithShadow("KS: \u00A75" + Integer.toString(laststats.getKillStreak())+"/"+Integer.toString(laststats.getMaxKillStreak()), x, y, 16777215);
				 y+=mc.fontRendererObj.FONT_HEIGHT;
			 }
		 }
	 }
}
