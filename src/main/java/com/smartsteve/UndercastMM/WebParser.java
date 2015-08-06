package com.smartsteve.UndercastMM;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WebParser {
	public int[] getkd(String name){
		/*
		 * 
		 * ReturnData
		 * int[0] = kill
		 * int[1] = death
		 * 
		 */
		String source = getHtml("https://oc.tc/"+name);                              
		String source2 = source;
		source = source.split("<h2 data-placement='top' rel='' title='")[1];
		source = source.split(" kills'>")[0];
		source2 = source2.split("<h2 data-placement='top' rel='' title='")[2];
		source2 = source2.split(" deaths'>")[0];
		return new int[]{Integer.parseInt(source) ,Integer.parseInt(source2)};
	}
	public String getHtml(String str) { // get Html source
		StringBuffer strbf = new StringBuffer();
		try {
		  URL url = new URL(str);

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
		return strbf.toString();
	}
}
