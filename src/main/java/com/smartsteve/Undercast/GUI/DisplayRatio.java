package com.smartsteve.Undercast.GUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.StatsData;

public class DisplayRatio {
	StatsData firststats,laststats;
	OptionData option;
	public DisplayRatio(StatsData first, StatsData last, OptionData op){
		MinecraftForge.EVENT_BUS.register(this);
		firststats = first;
		laststats = last;
		option = op;
	}
	 @SubscribeEvent
	    public void displayRatio(RenderGameOverlayEvent.Post e){
	    	Minecraft mc = Minecraft.getMinecraft();
	    	if (mc.inGameHasFocus && !mc.gameSettings.showDebugInfo&&e.type==RenderGameOverlayEvent.ElementType.AIR) {
	    		int x = 2, y = 2;
	    		
	    		if (option.fps){
	    			mc.fontRendererObj.drawStringWithShadow("FPS: "+mc.debug.split(",")[0].split(" fps")[0], x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    		if(option.kill){
	    			mc.fontRendererObj.drawStringWithShadow("Kill: \u00A7a" + Integer.toString(laststats.getKill()) + "/" + Integer.toString(firststats.getKill()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    		if(option.death){
	    			mc.fontRendererObj.drawStringWithShadow("Death: \u00A74" + Integer.toString(laststats.getDeath())+ "/" + Integer.toString(firststats.getDeath()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    		if(option.kd){
	    			mc.fontRendererObj.drawStringWithShadow("KD: \u00A73" + String.format("%.3f", (float)laststats.getKd()) + "/" + String.format("%.3f", (float)firststats.getKd()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    		if(option.kk){
	    			mc.fontRendererObj.drawStringWithShadow("KK: \u00A73" + String.format("%.3f", (float)laststats.getKk()) + "/" + String.format("%.3f", (float)firststats.getKk()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    		if(option.killstreak){
	    			mc.fontRendererObj.drawStringWithShadow("KS: \u00A75" + Integer.toString(laststats.getKillStreak())+"/"+Integer.toString(laststats.getMaxKillStreak()), x, y, 16777215);
	    			y+=mc.fontRendererObj.FONT_HEIGHT;
	    		}
	    	}
	    }
}
