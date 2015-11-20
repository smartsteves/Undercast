package com.smartsteve.Undercast;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandHandle extends CommandBase{
	OptionData option;
	ServerData server;
	public CommandHandle(OptionData o, ServerData s){
		option = o;
		server = s;
	}
	public String getName(){
		return "undercast";
	}
	public String getCommandUsage(ICommandSender s){
		return "Undercast Config Command";
	}
	public void execute(ICommandSender s, String[] args){
		if(args.length == 0){
			Util.message("Supporting arguments: enable, disable");
			System.out.println(server.toString());
			return;
		}
		if(args[0].equalsIgnoreCase("enable")){
			option.enable = true;
		}
		if(args[0].equalsIgnoreCase("disable")) {
			option.enable = false;
		}
	}
	public boolean canCommandSenderUse(ICommandSender sender){
		return true;
	}
}
