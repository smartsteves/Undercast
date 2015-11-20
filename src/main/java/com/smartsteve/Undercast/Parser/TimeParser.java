package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JUN on 2015-11-09.
 */
public class TimeParser extends TimerTask{
    ServerData server;
    public void run(){
        server.setPlaytime(server.getPlaytime()+1);
        server.setMatchtime(server.getMatchtime()+1);
    }
    public TimeParser(ServerData server){
        this.server = server;
    }
}
