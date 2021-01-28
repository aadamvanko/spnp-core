/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.transitions.probabilities.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 10ondr
 */
public class TransitionVisitorImplTest {
    private TransitionVisitorImpl instance;
    
    public TransitionVisitorImplTest() {
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
        instance = new TransitionVisitorImpl();
    }

    /**
     * Test of visit method, of class TransitionVisitorImpl.
     */
    @Test
    public void testVisit_ImmediateTransition() {
        ImmediateTransition immediateTransition = new ImmediateTransition(0, "ImmediateTransition123");
        // TODO: trailing zeroes
        String expected = String.format("imm(\"ImmediateTransition123\");%n" +
                                        "priority(\"ImmediateTransition123\", 0);%n" +
                                        "probval(\"ImmediateTransition123\", 0.000000);%n");
        instance.visit(immediateTransition);
        
        Assert.assertEquals("ImmediateTransition scenario simple", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = String.format("imm(\"ImmediateTransition123\");%n" +
                                 "priority(\"ImmediateTransition123\", 987);%n" +
                                 "guard(\"ImmediateTransition123\", ImmediateGuard);%n" +
                                 "probval(\"ImmediateTransition123\", 1000.000001);%n");
        
        Function<Integer> guard = new Function("ImmediateGuard", FunctionType.Guard, "return 4;", Integer.class);
        var probability = new ConstantTransitionProbability(1000.000001);
        immediateTransition = new ImmediateTransition(0, "ImmediateTransition123", 987, guard, probability);
        instance.visit(immediateTransition);
        
        Assert.assertEquals("ImmediateTransition scenario extended", expected.strip(), instance.getResult().strip());
    }
    
    /**
     * Test of visit method, of class TransitionVisitorImpl.
     */
    @Test
    public void testVisit_TimedTransition() {
        testDistribution("rateval", new ExponentialTransitionDistribution(1.0), new Double[]{1.0});
        testDistribution("detval", new ConstantTransitionDistribution(1.0), new Double[]{1.0});
        testDistribution("unifval", new UniformTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("geomval", new GeometricTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("weibval", new WeibullTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("normval", new TruncatedNormalTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("lognval", new LogarithmicNormalTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("erlval", new ErlangTransitionDIstribution(1.0, 2), new Double[]{1.0, 2.0});
        testDistribution("gamval", new GammaTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("betval", new BetaTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("cauval", new CauchyTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("binoval", new BinomialTransitionDistribution(1.0, 2.0, 3.0), new Double[]{1.0, 2.0, 3.0});
        testDistribution("poisval", new PoissonTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("parval", new ParetoTransitionDistribution(1.0, 2.0), new Double[]{1.0, 2.0});
        testDistribution("hyperval", new HyperExponentialTransitionDistribution(1.0, 2.0, 3.0), new Double[]{1.0, 2.0, 3.0});
        testDistribution("hypoval", new HypoExponentialTransitionDistribution(1, 2.0, 3.0, 4.0), new Double[]{1.0, 2.0, 3.0, 4.0});
        
        testDistribution("negbval", new NegativeBinomialTransitionDistribution(1.0, 2.0, 3.0), new Double[]{1.0, 2.0, 3.0});

        
        reinitVisitor();
        String expected = String.format("detval(\"TimedTransition789\", 1.000000);%n" +
                                 "priority(\"TimedTransition789\", 999);%n" +
                                 "guard(\"TimedTransition789\", TimedGuard);%n");
        
        Function<Integer> guard = new Function("TimedGuard", FunctionType.Guard, "return 7;", Integer.class);
        var constantDistribution = new ConstantTransitionDistribution(10000.00001);
        var timedTransition = new TimedTransition(14, "TimedTransition789", 999, guard, constantDistribution);
        instance.visit(timedTransition);
        
        Assert.assertEquals("TimedTransition scenario extended", expected.strip(), instance.getResult().strip());
    }
    
    private void testDistribution(String shortName, TransitionDistributionBase distribution, Double[] parameters){
        reinitVisitor();
        
        StringBuilder parametersBuilder = new StringBuilder();
        for(var par : parameters){
            // TODO: trailing zeroes
            parametersBuilder.append(String.format(", %.6f", par));
        }

        String expected = String.format("%s(\"TimedTransition789\"%s);%n" +
                                        "priority(\"TimedTransition789\", 0);%n", shortName, parametersBuilder.toString());
        var timedTransition = new TimedTransition(13, "TimedTransition789", distribution);
        instance.visit(timedTransition);
        
        Assert.assertEquals(String.format(  "TimedTransition scenario %s", distribution.getClass().getSimpleName()), 
                                            expected.strip(),
                                            instance.getResult().strip());
    }
}
