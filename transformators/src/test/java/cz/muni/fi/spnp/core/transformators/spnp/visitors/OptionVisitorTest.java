/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.options.*;
import org.junit.*;


/**
 *
 * @author 10ondr
 */
public class OptionVisitorTest {
    private OptionVisitor instance;
    
    public OptionVisitorTest() {
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
        instance = new OptionVisitor();
    }
    
    /**
     * Test of visit method, of class OptionVisitor.
     */
    @Test
    public void testVisit_ConstantTypeOption() {
        ConstantTypeOption constantTypeOption = new ConstantTypeOption(OptionKey.FOP_ABS_RET_M0, ConstantValue.VAL_SPLIT);
        String expected = "iopt(FOP_ABS_RET_M0, VAL_SPLIT);";
        instance.visit(constantTypeOption);
        Assert.assertEquals("ConstantTypeOption scenario 1", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        constantTypeOption = new ConstantTypeOption(OptionKey.FOP_SSPRES, ConstantValue.VAL_REPL);
        expected = "iopt(FOP_SSPRES, VAL_REPL);";
        instance.visit(constantTypeOption);
        Assert.assertEquals("ConstantTypeOption scenario 2", expected.strip(), instance.getResult().strip());
        
    }

    /**
     * Test of visit method, of class OptionVisitor.
     */
    @Test
    public void testVisit_DoubleTypeOption() {
        DoubleTypeOption doubleTypeOption = new DoubleTypeOption(OptionKey.FOP_FLUID_EPSILON, 55.46);
        String expected = "fopt(FOP_FLUID_EPSILON, 55.46);";
        instance.visit(doubleTypeOption);
        Assert.assertEquals("DoubleTypeOption scenario 1", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        doubleTypeOption = new DoubleTypeOption(OptionKey.FOP_SIM_ERROR, 1000000.000001);
        expected = "fopt(FOP_SIM_ERROR, 1000000.000001);";
        instance.visit(doubleTypeOption);
        Assert.assertEquals("DoubleTypeOption scenario 2", expected.strip(), instance.getResult().strip());
    }

    /**
     * Test of visit method, of class OptionVisitor.
     */
    @Test
    public void testVisit_IntegerTypeOption() {
        IntegerTypeOption integerTypeOption = new IntegerTypeOption(OptionKey.IOP_OK_VANLOOP, 5);
        String expected = "iopt(IOP_OK_VANLOOP, 5);";
        instance.visit(integerTypeOption);
        Assert.assertEquals("IntegerTypeOption scenario 1", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        integerTypeOption = new IntegerTypeOption(OptionKey.IOP_ELIMINATION, 9999999);
        expected = "iopt(IOP_ELIMINATION, 9999999);";
        instance.visit(integerTypeOption);
        Assert.assertEquals("IntegerTypeOption scenario 2", expected.strip(), instance.getResult().strip());
    }
}
