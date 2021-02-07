/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
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
        Function<Integer> intFunction = new Function<>("intFunc", FunctionType.SPNP, "{return 0;}", int.class);
        instance.visit(intFunction);
        Assert.assertEquals("FunctionDeclaration scenario integer", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "void voidFunc();";
        Function<Void> voidFunction = new Function<>("voidFunc", FunctionType.SPNP, "{return;}", Void.class);
        instance.visit(voidFunction);
        Assert.assertEquals("FunctionDeclaration scenario void", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "double doubleFunc();";
        Function<Double> doubleFunction = new Function<>("doubleFunc", FunctionType.SPNP, "{return 1.0;}", double.class);
        instance.visit(doubleFunction);
        Assert.assertEquals("FunctionDeclaration scenario double", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "string stringFunc();";
        Function<String> stringFunction = new Function<>("stringFunc", FunctionType.SPNP, "{return \"test\";}", String.class);
        instance.visit(stringFunction);
        Assert.assertEquals("FunctionDeclaration scenario string", expected.strip(), instance.getResult().strip());
        
        reinitVisitor();
        expected = "char charFunc();";
        Function<Character> charFunction = new Function<>("charFunc", FunctionType.SPNP, "{return 'c';}", Character.class);
        instance.visit(charFunction);
        Assert.assertEquals("FunctionDeclaration scenario char", expected.strip(), instance.getResult().strip());
    }
    
}
