/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.variables.DoubleVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.IntegerVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.VariableType;
import org.junit.*;


/**
 *
 * @author 10ondr
 */
public class VariableVisitorImplTest {
    private VariableVisitorImpl instance;
    
    public VariableVisitorImplTest() {
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
        instance = new VariableVisitorImpl();
    }

    /**
     * Test of visit method, of class VariableVisitorImpl.
     */
    @Test
    public void testVisit_DoubleVariable() {
        String expected = "double SampleDoubleVar123 = 100000.0012;";
        DoubleVariable doubleVariable = new DoubleVariable("SampleDoubleVar123", VariableType.Global, 100000.001200);
        instance.visit(doubleVariable);
        Assert.assertEquals("DoubleVariable scenario simple", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class VariableVisitorImpl.
     */
    @Test
    public void testVisit_IntegerVariable() {
        String expected = "int SampleIntegerVar123 = 100000;";
        IntegerVariable integerVariable = new IntegerVariable("SampleIntegerVar123", VariableType.Global, 100000);
        instance.visit(integerVariable);
        Assert.assertEquals("IntegerVariable scenario simple", expected.strip(), instance.getResult().strip());
    }
    
}
