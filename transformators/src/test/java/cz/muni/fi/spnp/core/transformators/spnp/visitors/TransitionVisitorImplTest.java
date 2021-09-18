/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ConstantTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ExponentialTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.elements.PolicyAffectedType;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPTimedTransition;
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
        immediateTransition.setCommentary("immediate transition comment");

        String expected = String.format("// immediate transition comment%n" +
                "imm(\"ImmediateTransition123\");%n" +
                "priority(\"ImmediateTransition123\", 0);%n" +
                "probval(\"ImmediateTransition123\", 1.0);%n");
        instance.visit(immediateTransition);

        Assert.assertEquals("ImmediateTransition scenario simple", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("// immediate transition comment%n" +
                "imm(\"ImmediateTransition123\");%n" +
                "priority(\"ImmediateTransition123\", 987);%n" +
                "guard(\"ImmediateTransition123\", ImmediateGuard);%n" +
                "probval(\"ImmediateTransition123\", 1000.000001);%n");

        FunctionSPNP<Integer> guard = new FunctionSPNP<>("ImmediateGuard", FunctionType.Guard, "return 4;", Integer.class);
        var probability = new ConstantTransitionProbability(1000.000001);
        immediateTransition = new ImmediateTransition(0, "ImmediateTransition123", 987, guard, probability);
        immediateTransition.setCommentary("immediate transition comment");
        instance.visit(immediateTransition);

        Assert.assertEquals("ImmediateTransition scenario extended", expected.strip(), instance.getResult().strip());
    }
    
    /**
     * Test of visit method, of class TransitionVisitorImpl.
     */
    @Test
    public void testVisit_TimedTransition() {
        String expected = String.format("// timed transition comment%n" +
                "rateval(\"TimedTransition789\", 1.0);%n" +
                "priority(\"TimedTransition789\", 0);%n");

        var timedTransitionFirst = new TimedTransition(1, "TimedTransition789", new ExponentialTransitionDistribution(1.0));
        timedTransitionFirst.setCommentary("timed transition comment");
        instance.visit(timedTransitionFirst);
        Assert.assertEquals("TimedTransition scenario simple", expected.strip(), instance.getResult().strip());


        reinitVisitor();
        expected = String.format("// timed transition comment%n" +
                "detval(\"TimedTransition789\", 10000.00001);%n" +
                "priority(\"TimedTransition789\", 999);%n" +
                "guard(\"TimedTransition789\", TimedGuard);%n");

        FunctionSPNP<Integer> guard = new FunctionSPNP<>("TimedGuard", FunctionType.Guard, "return 7;", Integer.class);
        var constantDistribution = new ConstantTransitionDistribution(10000.00001);
        var timedTransitionSecond = new TimedTransition(14, "TimedTransition789", 999, guard, constantDistribution);
        timedTransitionSecond.setCommentary("timed transition comment");
        instance.visit(timedTransitionSecond);

        Assert.assertEquals("TimedTransition scenario extended", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class TransitionVisitorImpl.
     */
    @Test
    public void testVisit_SPNPTimedTransition() {
        String expected = String.format("// spnp timed transition comment%n" +
                "rateval(\"SPNPTimedTransition789\", 1.0);%n" +
                "priority(\"SPNPTimedTransition789\", 0);%n" +
                "policy(\"SPNPTimedTransition789\", PRD);%n");
//                "affected(\"SPNPTimedTransition789\", \"PRD\");%n");

        var timedTransitionFirst = new SPNPTimedTransition(1, "SPNPTimedTransition789", new ExponentialTransitionDistribution(1.0));
        timedTransitionFirst.setCommentary("spnp timed transition comment");
        instance.visit(timedTransitionFirst);
        Assert.assertEquals("SPNPTimedTransition scenario simple", expected.strip(), instance.getResult().strip());


        reinitVisitor();
        expected = String.format("// spnp timed transition comment%n" +
                "detval(\"SPNPTimedTransition789\", 10000.00001);%n" +
                "priority(\"SPNPTimedTransition789\", 999);%n" +
                "policy(\"SPNPTimedTransition789\", PRI);%n" +
//                "affected(\"SPNPTimedTransition789\", \"PRS\");%n" +
                "guard(\"SPNPTimedTransition789\", TimedGuard);%n");

        FunctionSPNP<Integer> guard = new FunctionSPNP<>("TimedGuard", FunctionType.Guard, "return 7;", Integer.class);
        var constantDistribution = new ConstantTransitionDistribution(10000.00001);
        var timedTransitionSecond = new SPNPTimedTransition(14, "SPNPTimedTransition789", 999, guard, constantDistribution,
                PolicyAffectedType.PreemptiveRepeatIdentical/*, PolicyAffectedType.PreemptiveResume*/);
        timedTransitionSecond.setCommentary("spnp timed transition comment");
        instance.visit(timedTransitionSecond);

        Assert.assertEquals("SPNPTimedTransition scenario extended", expected.strip(), instance.getResult().strip());
    }

}
