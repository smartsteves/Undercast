package com.smartsteve.Undercast.DataContainer;

public class OptionData {
	public boolean enable;
	public boolean fps;
	public boolean kill,death,kk,kd,killstreak;
	public OptionData(){
		this(true,true,true,true,true,true,true);
	}
	public OptionData(boolean enable,boolean fps, boolean kill,boolean death,boolean kd, boolean kk, boolean killstreak){
		this.enable = enable;
		this.fps = fps;
		this.kill = kill;
		this.death = death;
		this.kd = kd;
		this.kk = kk;
		this.killstreak = killstreak;
	}
}
