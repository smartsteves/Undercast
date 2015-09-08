package com.smartsteve.Undercast;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandHandle extends CommandBase{
	public String getName(){
		return "undercast";
	}
	public String getCommandUsage(ICommandSender s){
		return "Undercast Config Command";
	}
	public void execute(ICommandSender s, String[] args){
		
	}
}
