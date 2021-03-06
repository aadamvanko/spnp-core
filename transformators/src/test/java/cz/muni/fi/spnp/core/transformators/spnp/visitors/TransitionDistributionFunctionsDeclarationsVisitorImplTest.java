/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.BetaTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.BinomialTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ConstantTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.HypoExponentialTransitionDistribution;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class TransitionDistributionFunctionsDeclarationsVisitorImplTest {
    private TransitionDistributionFunctionsDeclarationsVisitorImpl instance;
    private final FunctionSPNP<Double> guard1;
    private final FunctionSPNP<Double> guard2;
    private final FunctionSPNP<Double> guard3;
    
    private final FunctionSPNP<Integer> guardInt;
    
    public TransitionDistributionFunctionsDeclarationsVisitorImplTest() {
        guard1 = new FunctionSPNP<>("TimedGuard1", FunctionType.Distribution, "return 1.2;", double.class);
        guard2 = new FunctionSPNP<>("TimedGuard2", FunctionType.Distribution, "return 3.4;", double.class);
        guard3 = new FunctionSPNP<>("TimedGuard3", FunctionType.Distribution, "return 5.6;", double.class);
        
        guardInt = new FunctionSPNP<>("TimedGuardInteger", FunctionType.Distribution, "return 7;", int.class);
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
        instance = new TransitionDistributionFunctionsDeclarationsVisitorImpl();
    }
    
    /**
     * Test of visit method, of class TransitionDistributionFunctionsDeclarationsVisitorImpl.
     */
    @Test
    public void testVisit_SingleValueTransitionDistributionBase() {
        String expected = String.format("double TimedGuard1();%n");
        var distribution = new ConstantTransitionDistribution(guard1);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDeclaration scenario SingleValue", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDeclarationsVisitorImpl.
     */
    @Test
    public void testVisit_TwoValuesTransitionDistributionBase() {
        String expected = String.format("double TimedGuard1();%ndouble TimedGuard2();%n");
        var distribution = new BetaTransitionDistribution(guard1, guard2);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDeclaration scenario TwoValues", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDeclarationsVisitorImpl.
     */
    @Test
    public void testVisit_ThreeValuesTransitionDistributionBase() {
        String expected = String.format("double TimedGuard1();%ndouble TimedGuard2();%ndouble TimedGuard3();%n");
        var distribution = new BinomialTransitionDistribution(guard1, guard2, guard3);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDeclaration scenario ThreeValues", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionFunctionsDeclarationsVisitorImpl.
     */
    @Test
    public void testVisit_FourValuesTransitionDistributionBase() {
        String expected = String.format("int TimedGuardInteger();%ndouble TimedGuard1();%ndouble TimedGuard2();%ndouble TimedGuard3();%n");
        var distribution = new HypoExponentialTransitionDistribution(guardInt, guard1, guard2, guard3);
        instance.visit(distribution);
        Assert.assertEquals("TransitionDistributionFunctionDeclaration scenario FourValues", expected.strip(), instance.getResult().strip());
    }
    
}
