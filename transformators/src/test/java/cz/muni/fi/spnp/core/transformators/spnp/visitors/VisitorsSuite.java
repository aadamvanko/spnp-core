/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author 10ondr
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        cz.muni.fi.spnp.core.transformators.spnp.visitors.PlaceVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionProbabilityVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionDistributionVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.OptionVisitorTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.TransitionVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.ArcVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.VariableVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.DefineVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.IncludeVisitorImplTest.class,
        cz.muni.fi.spnp.core.transformators.spnp.visitors.FunctionDeclarationVisitorImplTest.class
})
public class VisitorsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
