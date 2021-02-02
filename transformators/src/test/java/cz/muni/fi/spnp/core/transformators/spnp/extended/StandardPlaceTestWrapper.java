package cz.muni.fi.spnp.core.transformators.spnp.extended;

import cz.muni.fi.spnp.core.models.places.StandardPlace;


public class StandardPlaceTestWrapper extends StandardPlace {
    
    private boolean wasDeclared = false;
    private boolean defaultValueInited = false;

    
    public StandardPlaceTestWrapper(int id, String name) {
        super(id, name);
    }
    
    public void setDeclared(boolean value){
        wasDeclared = value;
    }
    
    public boolean getDeclared(){
        return wasDeclared;
    }
    
    public void setDefaultValueInited(boolean value){
        defaultValueInited = value;
    }
    
    public boolean getDefaultValueInited(){
        return defaultValueInited;
    }
}
