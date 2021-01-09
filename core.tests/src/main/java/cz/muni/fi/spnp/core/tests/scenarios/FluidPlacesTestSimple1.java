package cz.muni.fi.spnp.core.tests.scenarios;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.tests.TransformatorTest;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.options.Option;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;
import java.util.HashSet;


public class FluidPlacesTestSimple1 extends TransformatorTest{

    public FluidPlacesTestSimple1(){
        super();
    }

    @Override
    protected SPNPCode createCode() {
        return new SPNPCode();
    }

    @Override
    protected SPNPOptions createOptions() {
        var parametersSet = new HashSet<InputParameter>();
        var optionsSet = new HashSet<Option>();
        return new SPNPOptions(parametersSet, optionsSet);
    }

    @Override
    protected PetriNet createPetriNet() {
        var net = new PetriNet();
        
        var fluidPlace1 = new FluidPlace(0, "FluidPlace1");
        var fluidPlace2 = new FluidPlace(1, "FluidPlace2");
        var fluidPlace3 = new FluidPlace(2, "FluidPlace3");
        var fluidPlace4 = new FluidPlace(3, "FluidPlace4");
        var fluidPlace5 = new FluidPlace(4, "FluidPlace5");
        
        fluidPlace1.setInitialValue(0.123);
        fluidPlace4.setInitialValue(9849.2615);
        fluidPlace5.setInitialValue(546.00001);

        fluidPlace3.setBoundValue(0.5654);
        fluidPlace4.setBoundValue(165.5654);
        fluidPlace5.setBoundValue(99999.00001);
        
        fluidPlace4.addBreakValue(123.01);
        fluidPlace4.addBreakValue(456.02);
        fluidPlace4.addBreakValue(789.03);
        fluidPlace4.addBreakValue(999.99);
        fluidPlace4.addBreakValue(987.789);
        fluidPlace5.addBreakValue(1.1);
        fluidPlace5.addBreakValue(7.7);
        
        net.addPlace(fluidPlace1);
        net.addPlace(fluidPlace2);
        net.addPlace(fluidPlace3);
        net.addPlace(fluidPlace4);
        net.addPlace(fluidPlace5);

        return net;
    }
}
