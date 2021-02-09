/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.code.Include;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class IncludeVisitorImplTest {
    private IncludeVisitorImpl instance;
    
    public IncludeVisitorImplTest() {
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
        instance = new IncludeVisitorImpl();
    }
    
    /**
     * Test of visit method, of class IncludeVisitorImpl.
     */
    @Test
    public void testVisit() {
        String expected = "#include <stdio.h>";
        var include = new Include("<stdio.h>");
        instance.visit(include);
        Assert.assertEquals("Include scenario simple", expected.strip(), instance.getResult().strip());
    }
    
}
