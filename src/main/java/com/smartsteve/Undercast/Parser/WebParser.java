package com.smartsteve.Undercast.Parser;

public class WebParser {
	// Old Source Code
	/*public StatsData parseData(String name){
		//This API was broken
		//OvercastAPI api = new OvercastAPI();
		//ParsedPlayer p = (ParsedPlayer)api.getPlayer(name);
		//Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("HOLA!!!!!!!!!!"));
		//return new StatsData(p.getGlobalKills(),(int)(p.getGlobalKills()*p.getGlobalKdRatio()),p.getGlobalDeaths());
		//return new StatsData();
		try{
			URL playerurl = new URL("https://oc.tc/users/"+name);
			HttpURLConnection playerhttp = (HttpURLConnection)playerurl.openConnection();
			JSONObject main = (JSONObject)JSONValue.parse(new InputStreamReader(playerhttp.getInputStream()));
			FileWriter file = new FileWriter("C:\\jsontest.json");
			file.write(main.toJSONString());
			file.flush();
			file.close();
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("ASDOPOW"));
		}
		catch(Throwable e){}
		return new StatsData();
	}*/
}
