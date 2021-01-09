/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.tests;

import cz.muni.fi.spnp.core.models.places.FluidPlace;
import java.util.HashMap;

/**
 *
 * @author 10ondr
 */
public class FluidPlaceTestWrapper extends FluidPlace {

    private boolean wasDeclared = false;
    private boolean defaultValueInited = false;
    private boolean boundValueInited = false;
    private final HashMap<Double, Boolean> breakValuesInited = new HashMap();
    
    public FluidPlaceTestWrapper(int id, String name) {
        super(id, name);
    }

    
    @Override
    public void addBreakValue(double breakValue) {
        super.addBreakValue(breakValue);
        
        breakValuesInited.put(breakValue, false);
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
    
    public void setBoundValueInited(boolean value){
        boundValueInited = value;
    }
    
    public boolean getBoundValueInited(){
        return boundValueInited;
    }
    
    public void setBreakValueInited(double breakVal, boolean initedVal){
        breakValuesInited.replace(breakVal, initedVal);
    }
    
    public HashMap getBreakValuesInited(){
        return breakValuesInited;
    }
}
