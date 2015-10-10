package com.smartsteve.Undercast;

import com.smartsteve.Undercast.DataContainer.OptionData;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandHandle extends CommandBase{
	OptionData option;
	public CommandHandle(OptionData o){
		option = o;
	}
	public String getName(){
		return "undercast";
	}
	public String getCommandUsage(ICommandSender s){
		return "Undercast Config Command";
	}
	public void execute(ICommandSender s, String[] args){
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("abc"));
		if(args[0].equalsIgnoreCase("enable")){
			option.enable = true;
		}
		if(args[0].equalsIgnoreCase("disable")){
			option.enable = false;
		}
		
	}
}
