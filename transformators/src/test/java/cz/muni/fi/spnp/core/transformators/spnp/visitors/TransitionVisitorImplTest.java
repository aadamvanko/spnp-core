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
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.distributions.*;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import org.junit.*;

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

        String expected = String.format("imm(\"ImmediateTransition123\");%n" +
                "priority(\"ImmediateTransition123\", 0);%n" +
                "probval(\"ImmediateTransition123\", 0.0);%n");
        instance.visit(immediateTransition);

        Assert.assertEquals("ImmediateTransition scenario simple", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("imm(\"ImmediateTransition123\");%n" +
                "priority(\"ImmediateTransition123\", 987);%n" +
                "guard(\"ImmediateTransition123\", ImmediateGuard);%n" +
                "probval(\"ImmediateTransition123\", 1000.000001);%n");

        Function<Integer> guard = new Function<>("ImmediateGuard", FunctionType.Guard, "return 4;", Integer.class);
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
        Function<Double> guard1 = new Function<>("TimedGuard1", FunctionType.Distribution, "return 1.2;", Double.class);
        Function<Double> guard2 = new Function<>("TimedGuard2", FunctionType.Distribution, "return 3.4;", Double.class);
        Function<Double> guard3 = new Function<>("TimedGuard3", FunctionType.Distribution, "return 5.6;", Double.class);
        
        Function<Integer> guardInt = new Function<>("TimedGuardInteger", FunctionType.Distribution, "return 7;", Integer.class);
        
        var dependentPlace = new StandardPlace(0, "SampleDependentPlace");
        
        testDistribution("rateval", new ExponentialTransitionDistribution(1.0), "1.0");
        testDistribution("ratefun", new ExponentialTransitionDistribution(guard1), "TimedGuard1");
        testDistribution("ratedep", new ExponentialTransitionDistribution(1.0, dependentPlace), "1.0, \"SampleDependentPlace\"");
        
        testDistribution("detval", new ConstantTransitionDistribution(1.0), "1.0");
        testDistribution("detfun", new ConstantTransitionDistribution(guard2), "TimedGuard2");
        testDistribution("detdep", new ConstantTransitionDistribution(1.0, dependentPlace), "1.0, \"SampleDependentPlace\"");
        
        testDistribution("unifval", new UniformTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("uniffun", new UniformTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("unifdep", new UniformTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("geomval", new GeometricTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("geomfun", new GeometricTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("geomdep", new GeometricTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");

        testDistribution("weibval", new WeibullTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("weibfun", new WeibullTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("weibdep", new WeibullTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("normval", new TruncatedNormalTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("normfun", new TruncatedNormalTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("normdep", new TruncatedNormalTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("lognval", new LogarithmicNormalTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("lognfun", new LogarithmicNormalTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("logndep", new LogarithmicNormalTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("erlval", new ErlangTransitionDIstribution(1.0, 2), "1.0, 2");
        testDistribution("erlfun", new ErlangTransitionDIstribution(guard2, guardInt), "TimedGuard2, TimedGuardInteger");
        testDistribution("erldep", new ErlangTransitionDIstribution(1.0, 2, dependentPlace), "1.0, 2, \"SampleDependentPlace\"");
        
        testDistribution("gamval", new GammaTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("gamfun", new GammaTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("gamdep", new GammaTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("betval", new BetaTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("betfun", new BetaTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("betdep", new BetaTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("cauval", new CauchyTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("caufun", new CauchyTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("caudep", new CauchyTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("binoval", new BinomialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("binofun", new BinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("binodep", new BinomialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
        
        testDistribution("poisval", new PoissonTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("poisfun", new PoissonTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("poisdep", new PoissonTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("parval", new ParetoTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("parfun", new ParetoTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("pardep", new ParetoTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
        
        testDistribution("hyperval", new HyperExponentialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("hyperfun", new HyperExponentialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hyperdep", new HyperExponentialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
        
        testDistribution("hypoval", new HypoExponentialTransitionDistribution(1, 2.0, 3.0, 4.0), "1, 2.0, 3.0, 4.0");
        testDistribution("hypofun", new HypoExponentialTransitionDistribution(guardInt, guard1, guard2, guard3), "TimedGuardInteger, TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hypodep", new HypoExponentialTransitionDistribution(1, 2.0, 3.0, 4.0, dependentPlace), "1, 2.0, 3.0, 4.0, \"SampleDependentPlace\"");

        testDistribution("negbval", new NegativeBinomialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("negbfun", new NegativeBinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("negbdep", new NegativeBinomialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");


        reinitVisitor();
        String expected = String.format("detval(\"TimedTransition789\", 10000.00001);%n" +
                "priority(\"TimedTransition789\", 999);%n" +
                "guard(\"TimedTransition789\", TimedGuard);%n");

        Function<Integer> guard = new Function<>("TimedGuard", FunctionType.Guard, "return 7;", Integer.class);
        var constantDistribution = new ConstantTransitionDistribution(10000.00001);
        var timedTransition = new TimedTransition(14, "TimedTransition789", 999, guard, constantDistribution);
        instance.visit(timedTransition);

        Assert.assertEquals("TimedTransition scenario extended", expected.strip(), instance.getResult().strip());
    }

    private void testDistribution(String shortName, TransitionDistributionBase distribution, String parameters) {
        reinitVisitor();

        String expected = String.format("%s(\"TimedTransition789\", %s);%n" +
                "priority(\"TimedTransition789\", 0);%n", shortName, parameters);
        var timedTransition = new TimedTransition(13, "TimedTransition789", distribution);
        instance.visit(timedTransition);

        Assert.assertEquals(String.format("TimedTransition scenario %s", distribution.getClass().getSimpleName()),
                expected.strip(),
                instance.getResult().strip());
    }
}
