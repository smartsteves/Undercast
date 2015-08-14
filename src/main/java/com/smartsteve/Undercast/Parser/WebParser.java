package com.smartsteve.Undercast.Parser;

import static me.ryanw.overcast.impl.OvercastAPI.getPlayer;
import me.ryanw.overcast.impl.object.ParsedPlayer;

import com.smartsteve.Undercast.DataContainer.StatsData;
public class WebParser {
	public static StatsData parseData(String name){
		ParsedPlayer p = (ParsedPlayer)getPlayer(name);
		return new StatsData(p.getGlobalKills(),(int)(p.getGlobalKills()*p.getGlobalKdRatio()),p.getGlobalDeaths());
		//return new StatsData();
	}
}
