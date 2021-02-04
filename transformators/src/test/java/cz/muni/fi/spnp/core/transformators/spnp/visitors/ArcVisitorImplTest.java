/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.arcs.*;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class ArcVisitorImplTest {
    private ArcVisitorImpl instance;
    
    private final StandardPlace sampleStandardPlace;
    private final FluidPlace sampleFluidPlace;
    private final ImmediateTransition sampleTransition;
    private final Function<Integer> sampleMultFunc;
    
    
    public ArcVisitorImplTest() {
        sampleStandardPlace = new StandardPlace(0, "StandardPlace123");
        sampleFluidPlace = new FluidPlace(0, "FluidPlace123");
        sampleTransition = new ImmediateTransition(1, "Transition456");
        sampleMultFunc = new Function<>("CardinalityFunction789", FunctionType.ArcCardinality, "return 9;", Integer.class);
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
        instance = new ArcVisitorImpl();
    }
    
    /**
     * Test of visit method, of class ArcVisitorImpl.
     */
    @Test
    public void testVisit_InhibitorArc() {
        // harc
        var harc = new InhibitorArc(2, sampleStandardPlace, sampleTransition);
        testArc("harc(\"Transition456\", \"StandardPlace123\");", harc, "InhibitorArc scenario harc");
        
        var dharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition);
        testArc("dharc(\"Transition456\", \"FluidPlace123\");", dharc, "InhibitorArc scenario dharc");

        // mharc
        var mharc = new InhibitorArc(2, sampleStandardPlace, sampleTransition, 9);
        testArc("mharc(\"Transition456\", \"StandardPlace123\", 9);", mharc, "InhibitorArc scenario mharc");
        
        var dmharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition, 9);
        testArc("dmharc(\"Transition456\", \"FluidPlace123\", 9);", dmharc, "InhibitorArc scenario dmharc");

        // vharc
        var vharc = new InhibitorArc(2, sampleStandardPlace, sampleTransition, sampleMultFunc);
        testArc("vharc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);", vharc, "InhibitorArc scenario vharc");
        
        var dvharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition, sampleMultFunc);
        testArc("dvharc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);", dvharc, "InhibitorArc scenario dvharc");
    }

    /**
     * Test of visit method, of class ArcVisitorImpl.
     */
    @Test
    public void testVisit_StandardArc() {
        // iarc
        var iarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        testArc("iarc(\"Transition456\", \"StandardPlace123\");", iarc, "StandardArc scenario iarc");
        
        var fiarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        fiarc.setFluid(true);
        testArc("fiarc(\"Transition456\", \"StandardPlace123\");", fiarc, "StandardArc scenario fiarc");

        var diarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition);
        testArc("diarc(\"Transition456\", \"FluidPlace123\");", diarc, "StandardArc scenario diarc");

        // oarc
        var oarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        testArc("oarc(\"Transition456\", \"StandardPlace123\");", oarc, "StandardArc scenario oarc");

        var foarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        foarc.setFluid(true);
        testArc("foarc(\"Transition456\", \"StandardPlace123\");", foarc, "StandardArc scenario fiarc");
        
        var doarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition);
        testArc("doarc(\"Transition456\", \"FluidPlace123\");", doarc, "StandardArc scenario doarc");
        
        // miarc
        var miarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, 9);
        testArc("miarc(\"Transition456\", \"StandardPlace123\", 9);", miarc, "StandardArc scenario miarc");
        
        var fmiarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, 9);
        fmiarc.setFluid(true);
        testArc("fmiarc(\"Transition456\", \"StandardPlace123\", 9);", fmiarc, "StandardArc scenario fmiarc");
        
        var dmiarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, 9);
        testArc("dmiarc(\"Transition456\", \"FluidPlace123\", 9);", dmiarc, "StandardArc scenario dmiarc");
        
        // moarc
        var moarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, 9);
        testArc("moarc(\"Transition456\", \"StandardPlace123\", 9);", moarc, "StandardArc scenario moarc");
        
        var fmoarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, 9);
        fmoarc.setFluid(true);
        testArc("fmoarc(\"Transition456\", \"StandardPlace123\", 9);", fmoarc, "StandardArc scenario fmoarc");
        
        var dmoarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, 9);
        testArc("dmoarc(\"Transition456\", \"FluidPlace123\", 9);", dmoarc, "StandardArc scenario dmoarc");
        
        // viarc
        var viarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        testArc("viarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);", viarc, "StandardArc scenario viarc");
        
        var fviarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fviarc.setFluid(true);
        testArc("fviarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);", fviarc, "StandardArc scenario fviarc");

        var dviarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, sampleMultFunc);
        testArc("dviarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);", dviarc, "StandardArc scenario dviarc");
        
        // voarc
        var voarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        testArc("voarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);", voarc, "StandardArc scenario voarc");
                
        var fvoarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fvoarc.setFluid(true);
        testArc("fvoarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);", fvoarc, "StandardArc scenario fvoarc");

        var dvoarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, sampleMultFunc);
        testArc("dvoarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);", dvoarc, "StandardArc scenario dvoarc");
        
    }
    
    private void testArc(String expected, Arc testedArc, String message){
        if(testedArc instanceof StandardArc)
            instance.visit((StandardArc) testedArc);
        else if(testedArc instanceof InhibitorArc)
            instance.visit((InhibitorArc) testedArc);
        Assert.assertEquals(message, expected.strip(), instance.getResult().strip());
        reinitVisitor();
    }
}
