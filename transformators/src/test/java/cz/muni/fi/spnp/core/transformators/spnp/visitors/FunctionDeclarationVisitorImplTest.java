/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class FunctionDeclarationVisitorImplTest {
    private FunctionDeclarationVisitorImpl instance;
    
    public FunctionDeclarationVisitorImplTest() {
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
        instance = new FunctionDeclarationVisitorImpl();
    }

    /**
     * Test of visit method, of class FunctionDeclarationVisitorImpl.
     */
    @Test
    public void testVisit() {
        String expected = "int intFunc();";
        FunctionSPNP<Integer> intFunction = new FunctionSPNP<>("intFunc", FunctionType.Other, "{return 0;}", int.class);
        instance.visit(intFunction);
        Assert.assertEquals("FunctionDeclaration scenario integer", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "void voidFunc();";
        FunctionSPNP<Void> voidFunction = new FunctionSPNP<>("voidFunc", FunctionType.Other, "{return;}", Void.class);
        instance.visit(voidFunction);
        Assert.assertEquals("FunctionDeclaration scenario void", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "double doubleFunc();";
        FunctionSPNP<Double> doubleFunction = new FunctionSPNP<>("doubleFunc", FunctionType.Other, "{return 1.0;}", double.class);
        instance.visit(doubleFunction);
        Assert.assertEquals("FunctionDeclaration scenario double", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "char * stringFunc();";
        FunctionSPNP<String> stringFunction = new FunctionSPNP<>("stringFunc", FunctionType.Other, "{return \"test\";}", String.class);
        instance.visit(stringFunction);
        Assert.assertEquals("FunctionDeclaration scenario string", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "char charFunc();";
        FunctionSPNP<Character> charFunction = new FunctionSPNP<>("charFunc", FunctionType.Other, "{return 'c';}", Character.class);
        instance.visit(charFunction);
        Assert.assertEquals("FunctionDeclaration scenario char", expected.strip(), instance.getResult().strip());
    }
    
}
