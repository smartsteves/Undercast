package com.smartsteve.Undercast.DataContainer;

/**
 * Created by JUN on 2015-10-10.
 */
public class ModData {
    public ModData(){
        this(false);
    }
    public ModData(boolean overcast){
        this.overcast = overcast;
    }
    public boolean isOvercast() {
        return overcast;
    }

    public void setOvercast(boolean overcast) {
        this.overcast = overcast;
    }

    private boolean overcast = true;

}
