package com.smartsteve.UndercastMM;

import java.io.IOException;
import java.lang.annotation.ElementType;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Logger;

class Data{
	int kill, killed, death, killstreak;                     //Need update while playing.   플레이 중간중간 업데이트
	int firstkill, firstdeath, firstkilled;                  //Read From oc.tc when Mod Load   젤 처음에 한번만 받아옴
	double kd,kk,Tkd,Tkk;                                    //Update when kill,killed,death update		kill,killed,death가 업데이트 될때 같이 업데이트됨												 
	int maxkillstreak;
	boolean containdata;
	public Data(){
		Tkk=kill=killed=death=killstreak=firstkill=firstdeath=maxkillstreak=firstkilled=0; //reset var
		kd=kk=Tkd=Tkk=0.00D;
		containdata =false;
	}
}
class Option{    // Feature on/off data contianer 켜기/끄기 관리
	boolean enable;
	boolean kd, kk, Tkd, killstreak,fps,tkill,tdeath,kill,death,Tkk;
	public Option(){
		Tkk=tkill=tdeath=kill=death=fps=enable=kd=kk=Tkd=killstreak=true;
	}
}

@Mod(modid = UndercastMM.MODID, version = UndercastMM.VERSION)
public class UndercastMM
{
	public static Logger LOGGER = FMLLog.getLogger();
    public static final String MODID = "Undercast--";
    public static final String VERSION = "1.8.0-0.0.1";  //Beta 1
    public Data d = new Data();
    public Option o = new Option();
    public WebParser wp = new WebParser();
    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
    	MinecraftForge.EVENT_BUS.register(this);
    	FMLCommonHandler.instance().bus().register(this);
    }
	/*
	 *         Rewrite code from UndercastClient V3(https://github.com/UndercastTeam/UndercastClient_v3/)
	 */
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e){
    	EntityPlayer p = Minecraft.getMinecraft().thePlayer;
    	IChatComponent c = e.message;
    	String message_unform = c.getUnformattedText();
    	if(message_unform.contains("Welcome")&&!d.containdata){
    		int[] tmp = wp.getkd(Minecraft.getMinecraft().thePlayer.getDisplayNameString());
    		d.firstkill = tmp[0];
    		d.firstdeath = tmp[1];
    		d.firstkilled = tmp[2];
    		d.containdata = true;
    	}
    	if (message_unform.startsWith("<") || message_unform.startsWith("[") || message_unform.startsWith("(From ") || message_unform.startsWith("(To ") || message_unform.startsWith("[Tip] ")) {
    		return;
    	}
    	if (message_unform.equals("Unknown command. Type \"/help\" for help.")) {
    		// We already know this message doesn't contain any information about Kill or Death 아무 정보도 포함하고 있지 않으므로 미리 걸러냄
    		return;
    	}
    	// Death
    	if (message_unform.startsWith(p.getDisplayNameString()) && !message_unform.toLowerCase().endsWith(" team")) {
    		// If you die from someone    누군가로부터 죽었을때(Killed)
    		if ((message_unform.contains(" by ") || message_unform.contains(" took ") || message_unform.contains(" fury of"))) {
    			d.killed++;
    		}
    		// Deaths
    		d.death++;
    		// reset killstreak
    		d.killstreak = 0;
    		updateRatio();
    		return;
    	}
    	if ((message_unform.contains("by " + p.getDisplayNameString()) || message_unform.contains(p.getDisplayNameString()+"'s")|| message_unform.contains("took " + p.getDisplayNameString()) || message_unform.contains("fury of " + p.getDisplayNameString())) && !message_unform.toLowerCase().contains(" destroyed by ")) {
    		// Killed a player
    		d.kill++;
    		// Killstreak
    		d.killstreak++;
    		// Update total killstreak if greater
    		if(d.killstreak>d.maxkillstreak) d.maxkillstreak = d.killstreak;
    		updateRatio();   	      
    	}
    }
    @SubscribeEvent
    public void displayRatio(RenderGameOverlayEvent.Post e){
    	Minecraft mc = Minecraft.getMinecraft();
    	if (mc.inGameHasFocus && !mc.gameSettings.showDebugInfo&&e.type==RenderGameOverlayEvent.ElementType.AIR) {
    		int x = 2, y = 2;
    		
    		if (o.fps){
    			mc.fontRendererObj.drawStringWithShadow("FPS: "+mc.debug.split(",")[0].split(" fps")[0], x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    		if(o.kill){
    			mc.fontRendererObj.drawStringWithShadow("Kill: \u00A7a" + Integer.toString(d.kill) + "/" + Integer.toString(d.firstkill+d.kill), x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    		if(o.death){
    			mc.fontRendererObj.drawStringWithShadow("Death: \u00A74" + Integer.toString(d.death)+ "/" + Integer.toString(d.firstdeath+d.death), x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    		if(o.kd){
    			mc.fontRendererObj.drawStringWithShadow("KD: \u00A73" + String.format("%.3f", (float)d.kd) + "/" + String.format("%.3f", (float)d.Tkd), x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    		if(o.kk){
    			mc.fontRendererObj.drawStringWithShadow("KK: \u00A73" + String.format("%.3f", (float)d.kk) + "/" + String.format("%.3f", (float)d.Tkk), x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    		if(o.killstreak){
    			mc.fontRendererObj.drawStringWithShadow("KS: \u00A75" + Integer.toString(d.killstreak)+"/"+Integer.toString(d.maxkillstreak), x, y, 16777215);
    			y+=mc.fontRendererObj.FONT_HEIGHT;
    		}
    	}
    }
    public void updateRatio(){
    	
    	//Update KD
    	double ratio_KD = 0.00D;
    	if(d.death!=0) ratio_KD = Double.valueOf(d.kill)/Double.valueOf(d.death);  // Death != 0
    	else ratio_KD = (double) d.kill;                                           // Death == 0
    	d.kd = ratio_KD;
    	
    	//Update Total KD
    	ratio_KD = 0.00D;
    	if((d.death+d.firstdeath)!=0) ratio_KD = Double.valueOf(d.kill+d.firstkill)/Double.valueOf(d.death+d.firstdeath);
    	else ratio_KD = (double) d.firstkill + d.kill;
    	d.Tkd = ratio_KD;
    	
    	//Update Total KK
    	double ratio_KK = 0.00D;
    	if((d.firstkilled+d.killed) != 0) ratio_KK = Double.valueOf(d.kill)/Double.valueOf(d.killed+d.firstkilled);
    	else ratio_KK = (double)d.kill;
    	d.Tkk = ratio_KK;
    	//Update KK
    	ratio_KK = 0.00D;
    	if(d.killed !=0) ratio_KK = Double.valueOf(d.kill)/Double.valueOf(d.killed);
    	else ratio_KK = (double)d.kill;
    	d.kk = ratio_KK;
    }
}
