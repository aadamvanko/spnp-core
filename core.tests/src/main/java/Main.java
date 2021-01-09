import cz.muni.fi.spnp.core.tests.TestPatterns;
import cz.muni.fi.spnp.core.tests.scenarios.FluidPlacesTestSimple1;
import cz.muni.fi.spnp.core.tests.scenarios.OptionsTestSimple1;
import cz.muni.fi.spnp.core.tests.scenarios.StandardPlacesTestSimple1;


public class Main {
    public static void main(String[] args){
        TestPatterns.init();
        
        var optionsTest = new OptionsTestSimple1();
        optionsTest.runTest();
        
        var stdPlacesTest = new StandardPlacesTestSimple1();
        stdPlacesTest.runTest();
        
        var fluidPlacesTest = new FluidPlacesTestSimple1();
        fluidPlacesTest.runTest();
    }
}
