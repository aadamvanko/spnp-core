/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.transitions.distributions.BetaTransitionDistribution;
import cz.muni.fi.spnp.core.models.transitions.distributions.BinomialTransitionDistribution;
import cz.muni.fi.spnp.core.models.transitions.distributions.ConstantTransitionDistribution;
import cz.muni.fi.spnp.core.models.transitions.distributions.HypoExponentialTransitionDistribution;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class TransitionDistributionFunctionsDefinitionsVisitorImplTest {
    private TransitionDistributionFunctionsDefinitionsVisitorImpl instance;
    private final Function<Double> guard1;
    private final Function<Double> guard2;
    private final Function<Double> guard3;
    
    private final Function<Integer> guardInt;

    public TransitionDistributionFunctionsDefinitionsVisitorImplTest() {
        guard1 = new Function<>("TimedGuard1", FunctionType.Distribution, "return 1.2;", double.class);
        guard2 = new Function<>("TimedGuard2", FunctionType.Distribution, "return 3.4;", double.class);
        guard3 = new Function<>("TimedGuard3", FunctionType.Distribution, "return 5.6;", double.class);
        
        guardInt = new Function<>("TimedGuardInteger", FunctionType.Distribution, "return 7;", int.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reinitVisitor();
    }
    
    @After
    public void tearDown() {
    }
    
    private void reinitVisitor(){
        instance = new TransitionDistributionFunctionsDefinitionsVisitorImpl();
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDefinitionsVisitorImpl.
     */
    @Test
    public void testVisit_SingleValueTransitionDistributionBase() {
        String expected = String.format(
                "double TimedGuard1() {%n"
                + "return 1.2;%n"
                + "}%n");
        
        var distribution = new ConstantTransitionDistribution(guard1);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDefinition scenario SingleValue", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDefinitionsVisitorImpl.
     */
    @Test
    public void testVisit_TwoValuesTransitionDistributionBase() {
        String expected = String.format(
                "double TimedGuard1() {%n"
                + "return 1.2;%n"
                + "}%n%n"
                        
                + "double TimedGuard2() {%n"
                + "return 3.4;%n"
                + "}%n");
        
        var distribution = new BetaTransitionDistribution(guard1, guard2);
        instance.visit(distribution);

        Assert.assertEquals("TransitionDistributionFunctionDefinition scenario TwoValues", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDefinitionsVisitorImpl.
     */
    @Test
    public void testVisit_ThreeValuesTransitionDistributionBase() {
        String expected = String.format(
                "double TimedGuard1() {%n"
                + "return 1.2;%n"
                + "}%n%n"
                        
                + "double TimedGuard2() {%n"
                + "return 3.4;%n"
                + "}%n%n"
                        
                + "double TimedGuard3() {%n"
                + "return 5.6;%n"
                + "}%n");
        
        var distribution = new BinomialTransitionDistribution(guard1, guard2, guard3);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDefinition scenario ThreeValues", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDefinitionsVisitorImpl.
     */
    @Test
    public void testVisit_FourValuesTransitionDistributionBase() {
        String expected = String.format(
        "int TimedGuardInteger() {%n"
        + "return 7;%n"
        + "}%n%n"

        + "double TimedGuard1() {%n"
        + "return 1.2;%n"
        + "}%n%n"

        + "double TimedGuard2() {%n"
        + "return 3.4;%n"
        + "}%n%n"

        + "double TimedGuard3() {%n"
        + "return 5.6;%n"
        + "}%n");

        var distribution = new HypoExponentialTransitionDistribution(guardInt, guard1, guard2, guard3);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDefinition scenario FourValues", expected.strip(), instance.getResult().strip());
    }
    
}
