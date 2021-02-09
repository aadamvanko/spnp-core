/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.code.Define;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class DefineVisitorImplTest {
    private DefineVisitorImpl instance;
    
    public DefineVisitorImplTest() {
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
        instance = new DefineVisitorImpl();
    }

    /**
     * Test of visit method, of class DefineVisitorImpl.
     */
    @Test
    public void testVisit() {
        String expected = "#define SampleDefine123 123";
        var defineInt = new Define("SampleDefine123", 123);
        instance.visit(defineInt);
        Assert.assertEquals("Define scenario integer", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "#define SampleDefineStr \"Defined String 123\"";
        var defineStr = new Define("SampleDefineStr", "Defined String 123");
        instance.visit(defineStr);
        Assert.assertEquals("Define scenario string", expected.strip(), instance.getResult().strip());
    }
    
}
