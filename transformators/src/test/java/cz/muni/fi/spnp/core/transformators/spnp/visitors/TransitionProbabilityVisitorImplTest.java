/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.FunctionalTransitionProbability;
import cz.muni.fi.spnp.core.models.transitions.probabilities.PlaceDependentTransitionProbability;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class TransitionProbabilityVisitorImplTest {
    TransitionProbabilityVisitorImpl instance;
    
    public TransitionProbabilityVisitorImplTest() {
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
        instance = new TransitionProbabilityVisitorImpl(new ImmediateTransition(0, "SampleTransition"));
    }
    
    /**
     * Test of visit method, of class TransitionProbabilityVisitorImpl.
     */
    @Test
    public void testVisit_ConstantTransitionProbability() {
        ConstantTransitionProbability constantTransitionProbability = new ConstantTransitionProbability(123);
        String expected = "probval(\"SampleTransition\", 123.0);";
        instance.visit(constantTransitionProbability);
        
        Assert.assertEquals("ConstantTransitionProbability scenario", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionProbabilityVisitorImpl.
     */
    @Test
    public void testVisit_FunctionalTransitionProbability() {
        Function<Double> function = new Function<>("funcName", FunctionType.Probability, "return 5.1;", Double.class);
        FunctionalTransitionProbability functionalTransitionProbability = new FunctionalTransitionProbability(function);
        // TODO: trailing zeroes
        String expected = "probfun(\"SampleTransition\", funcName);";
        instance.visit(functionalTransitionProbability);
        
        Assert.assertEquals("FunctionalTransitionProbability scenario", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionProbabilityVisitorImpl.
     */
    @Test
    public void testVisit_PlaceDependentTransitionProbability() {
        var stdPlace = new StandardPlace(0, "StandardPlace", 3);
        PlaceDependentTransitionProbability placeDependentTransitionProbability = new PlaceDependentTransitionProbability(12.34, stdPlace);
        // TODO: trailing zeroes
        String expected = "probdep(\"SampleTransition\", 12.34, \"StandardPlace\");";
        instance.visit(placeDependentTransitionProbability);
        
        Assert.assertEquals("PlaceDependentTransitionProbability scenario", expected.strip(), instance.getResult().strip());
    }
    
}
