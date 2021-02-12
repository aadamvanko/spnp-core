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
public class FunctionDefinitionVisitorImplTest {
    private FunctionDefinitionVisitorImpl instance;
    
    public FunctionDefinitionVisitorImplTest() {
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
        instance = new FunctionDefinitionVisitorImpl();
    }
    
    /**
     * Test of visit method, of class FunctionDefinitionVisitorImpl.
     */
    @Test
    public void testVisit() {
        String expected = String.format("int intFunc() {%n\treturn 0;%n}%n");
        Function<Integer> intFunction = new Function<>("intFunc", FunctionType.SPNP, "return 0;", int.class);
        instance.visit(intFunction);
        Assert.assertEquals("FunctionDeclaration scenario integer", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("void voidFunc() {%n\treturn;%n}%n");
        Function<Void> voidFunction = new Function<>("voidFunc", FunctionType.SPNP, "return;", Void.class);
        instance.visit(voidFunction);
        Assert.assertEquals("FunctionDeclaration scenario void", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("double doubleFunc() {%n\treturn 1.0;%n}%n");
        Function<Double> doubleFunction = new Function<>("doubleFunc", FunctionType.SPNP, "return 1.0;", double.class);
        instance.visit(doubleFunction);
        Assert.assertEquals("FunctionDeclaration scenario double", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("char * stringFunc() {%n\treturn \"test\";%n}%n");
        Function<String> stringFunction = new Function<>("stringFunc", FunctionType.SPNP, "return \"test\";", String.class);
        instance.visit(stringFunction);
        Assert.assertEquals("FunctionDeclaration scenario string", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = String.format("char charFunc() {%n\treturn 'c';%n}%n");
        Function<Character> charFunction = new Function<>("charFunc", FunctionType.SPNP, "return 'c';", Character.class);
        instance.visit(charFunction);
        Assert.assertEquals("FunctionDeclaration scenario char", expected.strip(), instance.getResult().strip());
    }
    
}
