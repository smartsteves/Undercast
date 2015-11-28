package com.smartsteve.Undercast.DataContainer;

import java.util.ArrayList;

/**
 * Created by JUN on 2015-11-27.
 */
public class RotationData {
    ArrayList<String> map;
    public RotationData(ArrayList<String> map){
        this.map = map;
    }
    public RotationData(){
        map = new ArrayList<String>(30);
    }
    public String getNextMap(String currentMap){
        return map.get(map.indexOf(currentMap)==map.size()?1:map.indexOf(currentMap)+1);
    }
}
