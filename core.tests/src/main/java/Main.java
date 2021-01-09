import cz.muni.fi.spnp.core.tests.TestPatterns;
import cz.muni.fi.spnp.core.tests.scenarios.FluidPlacesTestSimple;
import cz.muni.fi.spnp.core.tests.scenarios.OptionsTestSimple;
import cz.muni.fi.spnp.core.tests.scenarios.StandardPlacesTestSimple;


public class Main {
    public static void main(String[] args){
        TestPatterns.init();
        
        var optionsTest = new OptionsTestSimple();
        optionsTest.runTest();
        
        var stdPlacesTest = new StandardPlacesTestSimple();
        stdPlacesTest.runTest();
        
        var fluidPlacesTest = new FluidPlacesTestSimple();
        fluidPlacesTest.runTest();
    }
}
