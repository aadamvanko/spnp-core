/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp;

import cz.muni.fi.spnp.core.transformators.spnp.extended.TestPatterns;
import org.junit.*;

/**
 *
 * @author 10ondr
 */
public class SPNPTransformatorTest {
    
    public SPNPTransformatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TestPatterns.init();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of transform method, of class SPNPTransformator.
     */
    @Ignore
    @Test
    public void testTransform() {
        var testScenario = new TestScenarioExtended();
        
        if(!testScenario.runTest()){
            System.out.println("NOTE: The transformed result does NOT need to be precisely equal to the expected "
                    + "string for the test to pass. Please refer to the individual errors stated above.");
            
            Assert.assertEquals("Transformator extended scenario", testScenario.getExpectedResult().strip(), testScenario.getOutput().strip());
        }
    }
    
}
