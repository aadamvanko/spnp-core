/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.places.*;
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
public class PlaceVisitorImplTest {
    private PlaceVisitorImpl instance;
    
    public PlaceVisitorImplTest() {
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
        instance = new PlaceVisitorImpl();
    }
    
    /**
     * Test of visit method, of class PlaceVisitorImpl.
     */
    @Test
    public void testVisit_FluidPlace() {
        FluidPlace fluidPlace = new FluidPlace(0, "FluidPlace123");
        String expected = "fplace(\"FluidPlace123\");";
        instance.visit(fluidPlace);
        Assert.assertEquals("FluidPlace scenario simple", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        // TODO: trailing zeroes
        expected = String.format("fplace(\"FluidPlace123\");%n" +
                    "finit(\"FluidPlace123\", 9849.261500);%n" +
                    "fbound(\"FluidPlace123\", 165.565400);%n" +
                    "fbreak(\"FluidPlace123\", 1.000000);%n" +
                    "fbreak(\"FluidPlace123\", 123.010000);%n" +
                    "fbreak(\"FluidPlace123\", 10000.000010);%n");

        fluidPlace.setInitialValue(9849.2615);
        fluidPlace.setBoundValue(165.5654);
        fluidPlace.addBreakValue(123.01);
        fluidPlace.addBreakValue(10000.00001);
        fluidPlace.addBreakValue(1);
        
        instance.visit(fluidPlace);
        Assert.assertEquals("FluidPlace scenario extended", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class PlaceVisitorImpl.
     */
    @Test
    public void testVisit_StandardPlace() {
        StandardPlace standardPlace = new StandardPlace(9, "StdPlace789");
        String expected = "place(\"StdPlace789\");";
        instance.visit(standardPlace);
        Assert.assertEquals("StandardPlace scenario simple", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = String.format("place(\"StdPlace789\");%n" +
                    "init(\"StdPlace789\", 99999999);%n");

        standardPlace.setNumberOfTokens(99999999);
        
        instance.visit(standardPlace);
        Assert.assertEquals("StandardPlace scenario extended", expected.strip(), instance.getResult().strip());
    }
    
}
