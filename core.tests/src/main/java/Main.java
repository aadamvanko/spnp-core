import cz.muni.fi.spnp.core.tests.TestPatterns;
import cz.muni.fi.spnp.core.tests.options.OptionsTestSimple1;


public class Main {
    public static void main(String[] args){
        TestPatterns.init();
        
        var optionsTest = new OptionsTestSimple1();
        optionsTest.runTest();
    }
}
