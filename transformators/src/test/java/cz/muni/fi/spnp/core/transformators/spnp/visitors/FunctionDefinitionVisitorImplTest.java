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
 * @author 10ondr
 */
public class FunctionDefinitionVisitorImplTest {
    private FunctionDefinitionVisitorImpl instance;
    private final String comment;
    private final String commentExpected;

    public FunctionDefinitionVisitorImplTest() {
        comment = String.format("line1%nline2%nline3");
        commentExpected = String.format("/*%nline1%nline2%nline3%n*/%n");
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
        String expected = commentExpected + String.format("int intFunc() {%n\treturn 0;%n}%n");
        FunctionSPNP<Integer> intFunction = new FunctionSPNP<>("intFunc", FunctionType.Other, "return 0;", int.class);
        intFunction.setCommentary(comment);
        instance.visit(intFunction);
        Assert.assertEquals("FunctionDeclaration scenario integer", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = commentExpected + String.format("void voidFunc() {%n\treturn;%n}%n");
        FunctionSPNP<Void> voidFunction = new FunctionSPNP<>("voidFunc", FunctionType.Other, "return;", Void.class);
        voidFunction.setCommentary(comment);
        instance.visit(voidFunction);
        Assert.assertEquals("FunctionDeclaration scenario void", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = commentExpected + String.format("double doubleFunc() {%n\treturn 1.0;%n}%n");
        FunctionSPNP<Double> doubleFunction = new FunctionSPNP<>("doubleFunc", FunctionType.Other, "return 1.0;", double.class);
        doubleFunction.setCommentary(comment);
        instance.visit(doubleFunction);
        Assert.assertEquals("FunctionDeclaration scenario double", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = commentExpected + String.format("char * stringFunc() {%n\treturn \"test\";%n}%n");
        FunctionSPNP<String> stringFunction = new FunctionSPNP<>("stringFunc", FunctionType.Other, "return \"test\";", String.class);
        stringFunction.setCommentary(comment);
        instance.visit(stringFunction);
        Assert.assertEquals("FunctionDeclaration scenario string", expected.strip(), instance.getResult().strip());

        reinitVisitor();
        expected = commentExpected + String.format("char charFunc() {%n\treturn 'c';%n}%n");
        FunctionSPNP<Character> charFunction = new FunctionSPNP<>("charFunc", FunctionType.Other, "return 'c';", Character.class);
        charFunction.setCommentary(comment);
        instance.visit(charFunction);
        Assert.assertEquals("FunctionDeclaration scenario char", expected.strip(), instance.getResult().strip());
    }
    
}
