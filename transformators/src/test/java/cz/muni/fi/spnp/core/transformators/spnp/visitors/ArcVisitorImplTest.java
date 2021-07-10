/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.arcs.ArcDirection;
import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPInhibitorArc;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPStandardArc;
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
    private final FunctionSPNP<Integer> sampleMultFunc;
    
    
    public ArcVisitorImplTest() {
        sampleStandardPlace = new StandardPlace(0, "StandardPlace123");
        sampleFluidPlace = new FluidPlace(0, "FluidPlace123");
        sampleTransition = new ImmediateTransition(1, "Transition456");
        sampleMultFunc = new FunctionSPNP<>("CardinalityFunction789", FunctionType.ArcCardinality, "return 9;", Integer.class);
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
        harc.setCommentary("harc comment");
        testArc(addComment("// harc comment", "harc(\"Transition456\", \"StandardPlace123\");"), harc, "InhibitorArc scenario harc");

        var dharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition);
        dharc.setCommentary("dharc comment");
        testArc(addComment("// dharc comment", "dharc(\"Transition456\", \"FluidPlace123\");"), dharc, "InhibitorArc scenario dharc");

        // mharc
        var mharc = new InhibitorArc(2, sampleStandardPlace, sampleTransition, 9);
        mharc.setCommentary("mharc comment");
        testArc(addComment("// mharc comment", "mharc(\"Transition456\", \"StandardPlace123\", 9);"), mharc, "InhibitorArc scenario mharc");

        var dmharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition, 9);
        dmharc.setCommentary("dmharc comment");
        testArc(addComment("// dmharc comment", "dmharc(\"Transition456\", \"FluidPlace123\", 9);"), dmharc, "InhibitorArc scenario dmharc");

        // vharc
        var vharc = new InhibitorArc(2, sampleStandardPlace, sampleTransition, sampleMultFunc);
        vharc.setCommentary("vharc comment");
        testArc(addComment("// vharc comment", "vharc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), vharc, "InhibitorArc scenario vharc");

        var dvharc = new InhibitorArc(2, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dvharc.setCommentary("dvharc comment");
        testArc(addComment("// dvharc comment", "dvharc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dvharc, "InhibitorArc scenario dvharc");
    }

    /**
     * Test of visit method, of class ArcVisitorImpl.
     */
    @Test
    public void testVisit_SPNPInhibitorArc() {
        // harc
        var harc = new SPNPInhibitorArc(2, sampleStandardPlace, sampleTransition);
        harc.setCommentary("harc comment");
        testArc(addComment("// harc comment", "harc(\"Transition456\", \"StandardPlace123\");"), harc, "InhibitorArc scenario harc");

        var dharc = new SPNPInhibitorArc(2, sampleFluidPlace, sampleTransition);
        dharc.setCommentary("dharc comment");
        testArc(addComment("// dharc comment", "dharc(\"Transition456\", \"FluidPlace123\");"), dharc, "InhibitorArc scenario dharc");

        // mharc
        var mharc = new SPNPInhibitorArc(2, sampleStandardPlace, sampleTransition, "9");
        mharc.setCommentary("mharc comment");
        testArc(addComment("// mharc comment", "mharc(\"Transition456\", \"StandardPlace123\", 9);"), mharc, "InhibitorArc scenario mharc");

        var dmharc = new SPNPInhibitorArc(2, sampleFluidPlace, sampleTransition, "9");
        dmharc.setCommentary("dmharc comment");
        testArc(addComment("// dmharc comment", "dmharc(\"Transition456\", \"FluidPlace123\", 9);"), dmharc, "InhibitorArc scenario dmharc");

        // vharc
        var vharc = new SPNPInhibitorArc(2, sampleStandardPlace, sampleTransition, sampleMultFunc);
        vharc.setCommentary("vharc comment");
        testArc(addComment("// vharc comment", "vharc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), vharc, "InhibitorArc scenario vharc");

        var dvharc = new SPNPInhibitorArc(2, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dvharc.setCommentary("dvharc comment");
        testArc(addComment("// dvharc comment", "dvharc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dvharc, "InhibitorArc scenario dvharc");
    }

    /**
     * Test of visit method, of class ArcVisitorImpl.
     */
    @Test
    public void testVisit_StandardArc() {
        // iarc
        var iarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        iarc.setCommentary("iarc comment");
        testArc(addComment("// iarc comment", "iarc(\"Transition456\", \"StandardPlace123\");"), iarc, "StandardArc scenario iarc");

        var fiarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        fiarc.setFluid(true);
        fiarc.setCommentary("fiarc comment");
        testArc(addComment("// fiarc comment", "fiarc(\"Transition456\", \"StandardPlace123\");"), fiarc, "StandardArc scenario fiarc");

        var diarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition);
        diarc.setCommentary("diarc comment");
        testArc(addComment("// diarc comment", "diarc(\"Transition456\", \"FluidPlace123\");"), diarc, "StandardArc scenario diarc");

        // oarc
        var oarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        oarc.setCommentary("oarc comment");
        testArc(addComment("// oarc comment", "oarc(\"Transition456\", \"StandardPlace123\");"), oarc, "StandardArc scenario oarc");

        var foarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        foarc.setFluid(true);
        foarc.setCommentary("foarc comment");
        testArc(addComment("// foarc comment", "foarc(\"Transition456\", \"StandardPlace123\");"), foarc, "StandardArc scenario fiarc");

        var doarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition);
        doarc.setCommentary("doarc comment");
        testArc(addComment("// doarc comment", "doarc(\"Transition456\", \"FluidPlace123\");"), doarc, "StandardArc scenario doarc");

        // miarc
        var miarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, 9);
        miarc.setCommentary("miarc comment");
        testArc(addComment("// miarc comment", "miarc(\"Transition456\", \"StandardPlace123\", 9);"), miarc, "StandardArc scenario miarc");

        var fmiarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, 9);
        fmiarc.setFluid(true);
        fmiarc.setCommentary("fmiarc comment");
        testArc(addComment("// fmiarc comment", "fmiarc(\"Transition456\", \"StandardPlace123\", 9);"), fmiarc, "StandardArc scenario fmiarc");

        var dmiarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, 9);
        dmiarc.setCommentary("dmiarc comment");
        testArc(addComment("// dmiarc comment", "dmiarc(\"Transition456\", \"FluidPlace123\", 9);"), dmiarc, "StandardArc scenario dmiarc");

        // moarc
        var moarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, 9);
        moarc.setCommentary("moarc comment");
        testArc(addComment("// moarc comment", "moarc(\"Transition456\", \"StandardPlace123\", 9);"), moarc, "StandardArc scenario moarc");

        var fmoarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, 9);
        fmoarc.setFluid(true);
        fmoarc.setCommentary("fmoarc comment");
        testArc(addComment("// fmoarc comment", "fmoarc(\"Transition456\", \"StandardPlace123\", 9);"), fmoarc, "StandardArc scenario fmoarc");

        var dmoarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, 9);
        dmoarc.setCommentary("dmoarc comment");
        testArc(addComment("// dmoarc comment", "dmoarc(\"Transition456\", \"FluidPlace123\", 9);"), dmoarc, "StandardArc scenario dmoarc");

        // viarc
        var viarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        viarc.setCommentary("viarc comment");
        testArc(addComment("// viarc comment", "viarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), viarc, "StandardArc scenario viarc");

        var fviarc = new StandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fviarc.setFluid(true);
        fviarc.setCommentary("fviarc comment");
        testArc(addComment("// fviarc comment", "fviarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), fviarc, "StandardArc scenario fviarc");

        var dviarc = new StandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dviarc.setCommentary("dviarc comment");
        testArc(addComment("// dviarc comment", "dviarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dviarc, "StandardArc scenario dviarc");

        // voarc
        var voarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        voarc.setCommentary("voarc comment");
        testArc(addComment("// voarc comment", "voarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), voarc, "StandardArc scenario voarc");

        var fvoarc = new StandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fvoarc.setFluid(true);
        fvoarc.setCommentary("fvoarc comment");
        testArc(addComment("// fvoarc comment", "fvoarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), fvoarc, "StandardArc scenario fvoarc");

        var dvoarc = new StandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dvoarc.setCommentary("dvoarc comment");
        testArc(addComment("// dvoarc comment", "dvoarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dvoarc, "StandardArc scenario dvoarc");

    }

    /**
     * Test of visit method, of class ArcVisitorImpl.
     */
    @Test
    public void testVisit_SPNPStandardArc() {
        // iarc
        var iarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        iarc.setCommentary("iarc comment");
        testArc(addComment("// iarc comment", "iarc(\"Transition456\", \"StandardPlace123\");"), iarc, "StandardArc scenario iarc");

        var fiarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition);
        fiarc.setFluid(true);
        fiarc.setCommentary("fiarc comment");
        testArc(addComment("// fiarc comment", "fiarc(\"Transition456\", \"StandardPlace123\");"), fiarc, "StandardArc scenario fiarc");

        var diarc = new SPNPStandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition);
        diarc.setCommentary("diarc comment");
        testArc(addComment("// diarc comment", "diarc(\"Transition456\", \"FluidPlace123\");"), diarc, "StandardArc scenario diarc");

        // oarc
        var oarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        oarc.setCommentary("oarc comment");
        testArc(addComment("// oarc comment", "oarc(\"Transition456\", \"StandardPlace123\");"), oarc, "StandardArc scenario oarc");

        var foarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition);
        foarc.setFluid(true);
        foarc.setCommentary("foarc comment");
        testArc(addComment("// foarc comment", "foarc(\"Transition456\", \"StandardPlace123\");"), foarc, "StandardArc scenario fiarc");

        var doarc = new SPNPStandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition);
        doarc.setCommentary("doarc comment");
        testArc(addComment("// doarc comment", "doarc(\"Transition456\", \"FluidPlace123\");"), doarc, "StandardArc scenario doarc");

        // miarc
        var miarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, "9");
        miarc.setCommentary("miarc comment");
        testArc(addComment("// miarc comment", "miarc(\"Transition456\", \"StandardPlace123\", 9);"), miarc, "StandardArc scenario miarc");

        var fmiarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, "9");
        fmiarc.setFluid(true);
        fmiarc.setCommentary("fmiarc comment");
        testArc(addComment("// fmiarc comment", "fmiarc(\"Transition456\", \"StandardPlace123\", 9);"), fmiarc, "StandardArc scenario fmiarc");

        var dmiarc = new SPNPStandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, "9");
        dmiarc.setCommentary("dmiarc comment");
        testArc(addComment("// dmiarc comment", "dmiarc(\"Transition456\", \"FluidPlace123\", 9);"), dmiarc, "StandardArc scenario dmiarc");

        // moarc
        var moarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, "9");
        moarc.setCommentary("moarc comment");
        testArc(addComment("// moarc comment", "moarc(\"Transition456\", \"StandardPlace123\", 9);"), moarc, "StandardArc scenario moarc");

        var fmoarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, "9");
        fmoarc.setFluid(true);
        fmoarc.setCommentary("fmoarc comment");
        testArc(addComment("// fmoarc comment", "fmoarc(\"Transition456\", \"StandardPlace123\", 9);"), fmoarc, "StandardArc scenario fmoarc");

        var dmoarc = new SPNPStandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, "9");
        dmoarc.setCommentary("dmoarc comment");
        testArc(addComment("// dmoarc comment", "dmoarc(\"Transition456\", \"FluidPlace123\", 9);"), dmoarc, "StandardArc scenario dmoarc");

        // viarc
        var viarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        viarc.setCommentary("viarc comment");
        testArc(addComment("// viarc comment", "viarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), viarc, "StandardArc scenario viarc");

        var fviarc = new SPNPStandardArc(2, ArcDirection.Input, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fviarc.setFluid(true);
        fviarc.setCommentary("fviarc comment");
        testArc(addComment("// fviarc comment", "fviarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), fviarc, "StandardArc scenario fviarc");

        var dviarc = new SPNPStandardArc(2, ArcDirection.Input, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dviarc.setCommentary("dviarc comment");
        testArc(addComment("// dviarc comment", "dviarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dviarc, "StandardArc scenario dviarc");

        // voarc
        var voarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        voarc.setCommentary("voarc comment");
        testArc(addComment("// voarc comment", "voarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), voarc, "StandardArc scenario voarc");

        var fvoarc = new SPNPStandardArc(2, ArcDirection.Output, sampleStandardPlace, sampleTransition, sampleMultFunc);
        fvoarc.setFluid(true);
        fvoarc.setCommentary("fvoarc comment");
        testArc(addComment("// fvoarc comment", "fvoarc(\"Transition456\", \"StandardPlace123\", CardinalityFunction789);"), fvoarc, "StandardArc scenario fvoarc");

        var dvoarc = new SPNPStandardArc(2, ArcDirection.Output, sampleFluidPlace, sampleTransition, sampleMultFunc);
        dvoarc.setCommentary("dvoarc comment");
        testArc(addComment("// dvoarc comment", "dvoarc(\"Transition456\", \"FluidPlace123\", CardinalityFunction789);"), dvoarc, "StandardArc scenario dvoarc");

    }

    private String addComment(String comment, String rest) {
        return String.format("%s%n%s", comment, rest);
    }

    private void testArc(String expected, Arc testedArc, String message) {
        if (testedArc instanceof SPNPStandardArc)
            instance.visit((SPNPStandardArc) testedArc);
        else if (testedArc instanceof StandardArc)
            instance.visit((StandardArc) testedArc);
        else if (testedArc instanceof SPNPInhibitorArc)
            instance.visit((SPNPInhibitorArc) testedArc);
        else if (testedArc instanceof InhibitorArc)
            instance.visit((InhibitorArc) testedArc);
        else
            throw new AssertionError("Unsupported arc class");
        Assert.assertEquals(message, expected.strip(), instance.getResult().strip());
        reinitVisitor();
    }
}
