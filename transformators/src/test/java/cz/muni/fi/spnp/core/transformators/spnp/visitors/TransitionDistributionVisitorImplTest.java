/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionBase;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.*;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class TransitionDistributionVisitorImplTest {
    private TransitionDistributionVisitorImpl instance;
    
    private final FunctionSPNP<Double> guard1;
    private final FunctionSPNP<Double> guard2;
    private final FunctionSPNP<Double> guard3;

    private final FunctionSPNP<Integer> guardInt;

    private final StandardPlace dependentPlace;
    
    public TransitionDistributionVisitorImplTest() {
        guard1 = new FunctionSPNP<>("TimedGuard1", FunctionType.Distribution, "return 1.2;", Double.class);
        guard2 = new FunctionSPNP<>("TimedGuard2", FunctionType.Distribution, "return 3.4;", Double.class);
        guard3 = new FunctionSPNP<>("TimedGuard3", FunctionType.Distribution, "return 5.6;", Double.class);

        guardInt = new FunctionSPNP<>("TimedGuardInteger", FunctionType.Distribution, "return 7;", Integer.class);

        dependentPlace = new StandardPlace(0, "SampleDependentPlace");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void reinitVisitor(Transition transition){
        instance = new TransitionDistributionVisitorImpl(transition);
    }
    
    private void testDistribution(String shortName, TransitionDistributionBase distribution, String parameters) {
        var timedTransition = new TimedTransition(13, "TimedTransition789", distribution);
        reinitVisitor(timedTransition);

        String expected = String.format("%s(\"TimedTransition789\", %s);%n", shortName, parameters);

        if (distribution instanceof BetaTransitionDistribution)
            instance.visit((BetaTransitionDistribution) distribution);
        else if (distribution instanceof SPNPBetaTransitionDistribution)
            instance.visit((SPNPBetaTransitionDistribution) distribution);
        else if (distribution instanceof BinomialTransitionDistribution)
            instance.visit((BinomialTransitionDistribution) distribution);
        else if (distribution instanceof SPNPBinomialTransitionDistribution)
            instance.visit((SPNPBinomialTransitionDistribution) distribution);
        else if (distribution instanceof CauchyTransitionDistribution)
            instance.visit((CauchyTransitionDistribution) distribution);
        else if (distribution instanceof SPNPCauchyTransitionDistribution)
            instance.visit((SPNPCauchyTransitionDistribution) distribution);
        else if (distribution instanceof ConstantTransitionDistribution)
            instance.visit((ConstantTransitionDistribution) distribution);
        else if (distribution instanceof SPNPConstantTransitionDistribution)
            instance.visit((SPNPConstantTransitionDistribution) distribution);
        else if (distribution instanceof ErlangTransitionDIstribution)
            instance.visit((ErlangTransitionDIstribution) distribution);
        else if (distribution instanceof SPNPErlangTransitionDIstribution)
            instance.visit((SPNPErlangTransitionDIstribution) distribution);
        else if (distribution instanceof ExponentialTransitionDistribution)
            instance.visit((ExponentialTransitionDistribution) distribution);
        else if (distribution instanceof SPNPExponentialTransitionDistribution)
            instance.visit((SPNPExponentialTransitionDistribution) distribution);
        else if (distribution instanceof GammaTransitionDistribution)
            instance.visit((GammaTransitionDistribution) distribution);
        else if (distribution instanceof SPNPGammaTransitionDistribution)
            instance.visit((SPNPGammaTransitionDistribution) distribution);
        else if (distribution instanceof GeometricTransitionDistribution)
            instance.visit((GeometricTransitionDistribution) distribution);
        else if (distribution instanceof SPNPGeometricTransitionDistribution)
            instance.visit((SPNPGeometricTransitionDistribution) distribution);
        else if (distribution instanceof HyperExponentialTransitionDistribution)
            instance.visit((HyperExponentialTransitionDistribution) distribution);
        else if (distribution instanceof SPNPHyperExponentialTransitionDistribution)
            instance.visit((SPNPHyperExponentialTransitionDistribution) distribution);
        else if (distribution instanceof HypoExponentialTransitionDistribution)
            instance.visit((HypoExponentialTransitionDistribution) distribution);
        else if (distribution instanceof SPNPHypoExponentialTransitionDistribution)
            instance.visit((SPNPHypoExponentialTransitionDistribution) distribution);
        else if (distribution instanceof LogarithmicNormalTransitionDistribution)
            instance.visit((LogarithmicNormalTransitionDistribution) distribution);
        else if (distribution instanceof SPNPLogarithmicNormalTransitionDistribution)
            instance.visit((SPNPLogarithmicNormalTransitionDistribution) distribution);
        else if (distribution instanceof NegativeBinomialTransitionDistribution)
            instance.visit((NegativeBinomialTransitionDistribution) distribution);
        else if (distribution instanceof SPNPNegativeBinomialTransitionDistribution)
            instance.visit((SPNPNegativeBinomialTransitionDistribution) distribution);
        else if (distribution instanceof ParetoTransitionDistribution)
            instance.visit((ParetoTransitionDistribution) distribution);
        else if (distribution instanceof SPNPParetoTransitionDistribution)
            instance.visit((SPNPParetoTransitionDistribution) distribution);
        else if (distribution instanceof PoissonTransitionDistribution)
            instance.visit((PoissonTransitionDistribution) distribution);
        else if (distribution instanceof SPNPPoissonTransitionDistribution)
            instance.visit((SPNPPoissonTransitionDistribution) distribution);
        else if (distribution instanceof TruncatedNormalTransitionDistribution)
            instance.visit((TruncatedNormalTransitionDistribution) distribution);
        else if (distribution instanceof SPNPTruncatedNormalTransitionDistribution)
            instance.visit((SPNPTruncatedNormalTransitionDistribution) distribution);
        else if (distribution instanceof UniformTransitionDistribution)
            instance.visit((UniformTransitionDistribution) distribution);
        else if (distribution instanceof SPNPUniformTransitionDistribution)
            instance.visit((SPNPUniformTransitionDistribution) distribution);
        else if (distribution instanceof WeibullTransitionDistribution)
            instance.visit((WeibullTransitionDistribution) distribution);
        else if (distribution instanceof SPNPWeibullTransitionDistribution)
            instance.visit((SPNPWeibullTransitionDistribution) distribution);
        else
            throw new AssertionError("Unknown distribution type " + distribution.getClass());

        Assert.assertEquals(String.format("TimedTransition scenario %s", distribution.getClass().getSimpleName()),
                expected.strip(),
                instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_BetaTransitionDistribution() {
        testDistribution("betval", new BetaTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("betfun", new BetaTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("betdep", new BetaTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPBetaTransitionDistribution() {
        testDistribution("betval", new SPNPBetaTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("betfun", new SPNPBetaTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("betdep", new SPNPBetaTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_BinomialTransitionDistribution() {
        testDistribution("binoval", new BinomialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("binofun", new BinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("binodep", new BinomialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPBinomialTransitionDistribution() {
        testDistribution("binoval", new SPNPBinomialTransitionDistribution("1.0", "2.0", "3.0"), "1.0, 2.0, 3.0");
        testDistribution("binofun", new SPNPBinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("binodep", new SPNPBinomialTransitionDistribution("1.0", "2.0", "3.0", dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_CauchyTransitionDistribution() {
        testDistribution("cauval", new CauchyTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("caufun", new CauchyTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("caudep", new CauchyTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPCauchyTransitionDistribution() {
        testDistribution("cauval", new SPNPCauchyTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("caufun", new SPNPCauchyTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("caudep", new SPNPCauchyTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_ConstantTransitionDistribution() {
        testDistribution("detval", new ConstantTransitionDistribution(1.0), "1.0");
        testDistribution("detfun", new ConstantTransitionDistribution(guard2), "TimedGuard2");
        testDistribution("detdep", new ConstantTransitionDistribution(1.0, dependentPlace), "1.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPConstantTransitionDistribution() {
        testDistribution("detval", new SPNPConstantTransitionDistribution("1.0"), "1.0");
        testDistribution("detfun", new SPNPConstantTransitionDistribution(guard2), "TimedGuard2");
        testDistribution("detdep", new SPNPConstantTransitionDistribution("1.0", dependentPlace), "1.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_ErlangTransitionDIstribution() {
        testDistribution("erlval", new ErlangTransitionDIstribution(1.0, 2), "1.0, 2");
        testDistribution("erlfun", new ErlangTransitionDIstribution(guard2, guardInt), "TimedGuard2, TimedGuardInteger");
        testDistribution("erldep", new ErlangTransitionDIstribution(1.0, 2, dependentPlace), "1.0, 2, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPErlangTransitionDIstribution() {
        testDistribution("erlval", new SPNPErlangTransitionDIstribution("1.0", "2"), "1.0, 2");
        testDistribution("erlfun", new SPNPErlangTransitionDIstribution(guard2, guardInt), "TimedGuard2, TimedGuardInteger");
        testDistribution("erldep", new SPNPErlangTransitionDIstribution("1.0", "2", dependentPlace), "1.0, 2, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_ExponentialTransitionDistribution() {
        testDistribution("rateval", new ExponentialTransitionDistribution(1.0), "1.0");
        testDistribution("ratefun", new ExponentialTransitionDistribution(guard1), "TimedGuard1");
        testDistribution("ratedep", new ExponentialTransitionDistribution(1.0, dependentPlace), "1.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPExponentialTransitionDistribution() {
        testDistribution("rateval", new SPNPExponentialTransitionDistribution("1.0"), "1.0");
        testDistribution("ratefun", new SPNPExponentialTransitionDistribution(guard1), "TimedGuard1");
        testDistribution("ratedep", new SPNPExponentialTransitionDistribution("1.0", dependentPlace), "1.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_GammaTransitionDistribution() {
        testDistribution("gamval", new GammaTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("gamfun", new GammaTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("gamdep", new GammaTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPGammaTransitionDistribution() {
        testDistribution("gamval", new SPNPGammaTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("gamfun", new SPNPGammaTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("gamdep", new SPNPGammaTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_GeometricTransitionDistribution() {
        testDistribution("geomval", new GeometricTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("geomfun", new GeometricTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("geomdep", new GeometricTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPGeometricTransitionDistribution() {
        testDistribution("geomval", new SPNPGeometricTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("geomfun", new SPNPGeometricTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("geomdep", new SPNPGeometricTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_HyperExponentialTransitionDistribution() {
        testDistribution("hyperval", new HyperExponentialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("hyperfun", new HyperExponentialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hyperdep", new HyperExponentialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPHyperExponentialTransitionDistribution() {
        testDistribution("hyperval", new SPNPHyperExponentialTransitionDistribution("1.0", "2.0", "3.0"), "1.0, 2.0, 3.0");
        testDistribution("hyperfun", new SPNPHyperExponentialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hyperdep", new SPNPHyperExponentialTransitionDistribution("1.0", "2.0", "3.0", dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_HypoExponentialTransitionDistribution() {
        testDistribution("hypoval", new HypoExponentialTransitionDistribution(2, 2.0, 3.0, 4.0), "2.0, 2.0, 3.0, 4.0");
        testDistribution("hypofun", new HypoExponentialTransitionDistribution(guardInt, guard1, guard2, guard3), "TimedGuardInteger, TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hypodep", new HypoExponentialTransitionDistribution(3, 2.0, 3.0, 4.0, dependentPlace), "3.0, 2.0, 3.0, 4.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPHypoExponentialTransitionDistribution() {
        testDistribution("hypoval", new SPNPHypoExponentialTransitionDistribution("2", "2.0", "3.0", "4.0"), "2, 2.0, 3.0, 4.0");
        testDistribution("hypofun", new SPNPHypoExponentialTransitionDistribution(guardInt, guard1, guard2, guard3), "TimedGuardInteger, TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("hypodep", new SPNPHypoExponentialTransitionDistribution("3", "2.0", "3.0", "4.0", dependentPlace), "3, 2.0, 3.0, 4.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_LogarithmicNormalTransitionDistribution() {
        testDistribution("lognval", new LogarithmicNormalTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("lognfun", new LogarithmicNormalTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("logndep", new LogarithmicNormalTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPLogarithmicNormalTransitionDistribution() {
        testDistribution("lognval", new SPNPLogarithmicNormalTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("lognfun", new SPNPLogarithmicNormalTransitionDistribution(guard2, guard3), "TimedGuard2, TimedGuard3");
        testDistribution("logndep", new SPNPLogarithmicNormalTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_NegativeBinomialTransitionDistribution() {
        testDistribution("negbval", new NegativeBinomialTransitionDistribution(1.0, 2.0, 3.0), "1.0, 2.0, 3.0");
        testDistribution("negbfun", new NegativeBinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("negbdep", new NegativeBinomialTransitionDistribution(1.0, 2.0, 3.0, dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPNegativeBinomialTransitionDistribution() {
        testDistribution("negbval", new SPNPNegativeBinomialTransitionDistribution("1.0", "2.0", "3.0"), "1.0, 2.0, 3.0");
        testDistribution("negbfun", new SPNPNegativeBinomialTransitionDistribution(guard1, guard2, guard3), "TimedGuard1, TimedGuard2, TimedGuard3");
        testDistribution("negbdep", new SPNPNegativeBinomialTransitionDistribution("1.0", "2.0", "3.0", dependentPlace), "1.0, 2.0, 3.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_ParetoTransitionDistribution() {
        testDistribution("parval", new ParetoTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("parfun", new ParetoTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("pardep", new ParetoTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPParetoTransitionDistribution() {
        testDistribution("parval", new SPNPParetoTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("parfun", new SPNPParetoTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("pardep", new SPNPParetoTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_PoissonTransitionDistribution() {
        testDistribution("poisval", new PoissonTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("poisfun", new PoissonTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("poisdep", new PoissonTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPPoissonTransitionDistribution() {
        testDistribution("poisval", new SPNPPoissonTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("poisfun", new SPNPPoissonTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("poisdep", new SPNPPoissonTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_TruncatedNormalTransitionDistribution() {
        testDistribution("normval", new TruncatedNormalTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("normfun", new TruncatedNormalTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("normdep", new TruncatedNormalTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPTruncatedNormalTransitionDistribution() {
        testDistribution("normval", new SPNPTruncatedNormalTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("normfun", new SPNPTruncatedNormalTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("normdep", new SPNPTruncatedNormalTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_UniformTransitionDistribution() {
        testDistribution("unifval", new UniformTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("uniffun", new UniformTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("unifdep", new UniformTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPUniformTransitionDistribution() {
        testDistribution("unifval", new SPNPUniformTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("uniffun", new SPNPUniformTransitionDistribution(guard3, guard1), "TimedGuard3, TimedGuard1");
        testDistribution("unifdep", new SPNPUniformTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_WeibullTransitionDistribution() {
        testDistribution("weibval", new WeibullTransitionDistribution(1.0, 2.0), "1.0, 2.0");
        testDistribution("weibfun", new WeibullTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("weibdep", new WeibullTransitionDistribution(1.0, 2.0, dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

    /**
     * Test of visit method, of class TransitionDistributionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPWeibullTransitionDistribution() {
        testDistribution("weibval", new SPNPWeibullTransitionDistribution("1.0", "2.0"), "1.0, 2.0");
        testDistribution("weibfun", new SPNPWeibullTransitionDistribution(guard1, guard2), "TimedGuard1, TimedGuard2");
        testDistribution("weibdep", new SPNPWeibullTransitionDistribution("1.0", "2.0", dependentPlace), "1.0, 2.0, \"SampleDependentPlace\"");
    }

}
