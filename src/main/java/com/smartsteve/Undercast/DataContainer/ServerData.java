package com.smartsteve.Undercast.DataContainer;

public class ServerData {
	private String server;
	private String map, nextMap;
	private String team;
	private String teamColor;
	private String ping;
	private int playtime;
	private int matchtime;
	public ServerData(){
		this("","","","", "", 0);
	}
	public ServerData(String server, String map, String nextMap, String team, String teamColor, int matchtime){
		this.server = server;
		this.map = map;
		this.team = team;
		this.nextMap = nextMap;
		this.matchtime = matchtime;
		this.teamColor = teamColor;
	}
	public void setServer(String server){
		this.server = server;
	}
	public void setMap(String map){
		this.map = map;
	}
	public void setTeam(String team){
		this.team = team;
	}
	public void setNextMap(String nextMap){
		this.nextMap = nextMap;
	}
	public void setMatchTime(int matchtime){
		this.matchtime = matchtime;
	}
	public void setTeamColor(String teamColor){
		this.teamColor = teamColor;
	}
    public void setPing(String ping){ this.ping = ping; }
	public String getServer(){ return server; }
	public String getMap(){
		return map;
	}
	public String getTeam(){
		return team;
	}
	public String getCurrentMap(){
		return this.map;
	}
	public String getNextMap(){
		return this.nextMap;
	}
	public String getTeamColor(){
		return this.teamColor;
	}
    public String getPing(){ return this.ping; }
	public boolean isLobby(){
		if(server.startsWith("Lobby")||server.contains("Lobby")||server.equalsIgnoreCase("lobby"))return true;
		return false;
	}
}
