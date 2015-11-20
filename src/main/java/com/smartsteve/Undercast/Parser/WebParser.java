package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WebParser {
	// Old Source Code
	//Use it because OvercastStatsAPI didn't work
	// int[0] = total kill
	// int[1] = total death
	public static int[] getPlayerStat(String name){
		int[] returnData = new int[10];
		StringBuffer strbf = new StringBuffer();
		try {
			URL url = new URL("https://oc.tc/users/"+name);

			InputStream is = url.openConnection().getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String read;
			while((read = br.readLine()) != null) {
				strbf.append(read);
			}
			isr.close();
			br.close();
		}
		catch(Exception error) {
			System.out.println("Error : " + error);
		}
		String s = strbf.toString();
		returnData[0] = Integer.parseInt(s.split("<div class='number' data-placement='top' rel='' title='")[1].split(" kills")[0]);
		returnData[1] = Integer.parseInt(s.split("<div class='number' data-placement='top' rel='' title='")[2].split(" deaths")[0]);
		return returnData;
	}

}

