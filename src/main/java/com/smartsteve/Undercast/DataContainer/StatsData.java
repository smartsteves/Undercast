package com.smartsteve.Undercast.DataContainer;

public class StatsData {
	private int kill, killed, death, killstreak;                     //Need update while playing.   
	private double kd,kk;                                    //Update when kill,killed,death update		kill,killed,death								 
	private int maxkillstreak;
	public StatsData(){
		this(0,0,0);
	}
	public StatsData(int tkill, int tkilled, int tdeath){
		kill = tkill;
		killed = tkilled;
		death = tdeath;
		killstreak=0;
		maxkillstreak=0;
		update();
	}
	private void update(){
		if(death==0)kd=kill;
		else kd=Double.valueOf(Double.valueOf(kill)/Double.valueOf(death));
		if(killed==0)kk=kill;
		else kk=Double.valueOf(Double.valueOf(kill)/Double.valueOf(killed));
	}
	public void setKill(int t){
		kill = t;
		update();
	}
	public void setKilled(int t){
		killed = t;
		update();
	}
	public void setDeath(int t){
		death = t;
		update();
	}
	public int getKill(){
		return kill;
	}
	public int getKilled(){
		return killed;
	}
	public int getDeath(){
		return death;
	}
	public int getKillStreak(){
		return killstreak;
	}
	public double getKd(){
		return kd;
	}
	public double getKk(){
		return kk;
	}
	public int getMaxKillStreak(){
		return maxkillstreak;
	}
	public void addKill(){
		kill++;
		killstreak++;
		if(killstreak>maxkillstreak) maxkillstreak = killstreak;
		update();
	}
	public void addKilled(){
		killed++;
		death++;
		killstreak = 0;
		update();
	}
	public void addDeath(){
		death++;
		killstreak = 0;
		update();
	}
}
