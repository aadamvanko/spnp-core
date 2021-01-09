package cz.muni.fi.spnp.core.tests.scenarios;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.tests.TransformatorTest;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.options.Option;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;
import java.util.HashSet;


public class StandardPlacesTestSimple extends TransformatorTest{

    public StandardPlacesTestSimple(){
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
        
        var stdPlace1 = new StandardPlace(0, "StdPlace1");
        var stdPlace2 = new StandardPlace(1, "StdPlace2");
        var stdPlace3 = new StandardPlace(2, "StdPlace3");
        var stdPlace4 = new StandardPlace(3, "StdPlace4");
        var stdPlace5 = new StandardPlace(4, "StdPlace5");
        
        stdPlace3.setNumberOfTokens(5);
        stdPlace5.setNumberOfTokens(99999999);
        net.addPlace(stdPlace1);
        net.addPlace(stdPlace2);
        net.addPlace(stdPlace3);
        net.addPlace(stdPlace4);
        net.addPlace(stdPlace5);
        
        return net;
    }
}
