package cz.muni.fi.spnp.core.transformators.spnp;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.arcs.ArcDirection;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.probabilities.ConstantTransitionProbability;
import cz.muni.fi.spnp.core.transformators.spnp.extended.TransformatorTest;
import cz.muni.fi.spnp.core.transformators.spnp.options.*;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.DoubleInputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.IntegerInputParameter;

import java.util.HashSet;


public class TestScenarioExtended extends TransformatorTest {
    private final String expected;
    
    public TestScenarioExtended(){
        super();

        expected = String.format("void options() {%n" +
                "	iopt(IOP_SSMETHOD, 666666666);%n" +
                "	iopt(FOP_SSPRES, VAL_REPL);%n" +
                "	fopt(FOP_SIM_ERROR, 55.46);%n" +
                "	iopt(IOP_ELIMINATION, 125);%n" +
                "	iopt(IOP_OK_VANLOOP, 5);%n" +
                "	iopt(FOP_ABS_RET_M0, VAL_SPLIT);%n" +
                "	param1 = input(\"prompt1\");%n" +
                "	param2 = input(\"prompt2\");%n" +
                "	param3 = finput(\"prompt3\");%n" +
                "	param4 = finput(\"prompt4\");%n" +
                "}%n" +
                "void net() {%n" +
                "	fplace(\"FluidPlace1\");%n" +
                "	finit(\"FluidPlace1\", 0.123);%n" +
                "	fplace(\"FluidPlace2\");%n" +
                "	fplace(\"FluidPlace3\");%n" +
                "	fbound(\"FluidPlace3\", 0.5654);%n" +
                "	fplace(\"FluidPlace4\");%n" +
                "	finit(\"FluidPlace4\", 9849.2615);%n" +
                "	fbound(\"FluidPlace4\", 165.5654);%n" +
                "	fbreak(\"FluidPlace4\", 456.02);%n" +
                "	fbreak(\"FluidPlace4\", 987.789);%n" +
                "	fbreak(\"FluidPlace4\", 123.01);%n" +
                "	fbreak(\"FluidPlace4\", 999.99);%n" +
                "	fbreak(\"FluidPlace4\", 789.03);%n" +
                "	fplace(\"FluidPlace5\");%n" +
                "	finit(\"FluidPlace5\", 546.00001);%n" +
                "	fbound(\"FluidPlace5\", 99999.00001);%n" +
                "	fbreak(\"FluidPlace5\", 7.7);%n" +
                "	fbreak(\"FluidPlace5\", 1.1);%n" +
                "	place(\"StdPlace1\");%n" +
                "	place(\"StdPlace2\");%n" +
                "	place(\"StdPlace3\");%n" +
                "	init(\"StdPlace3\", 5);%n" +
                "	place(\"StdPlace4\");%n" +
                "	place(\"StdPlace5\");%n" +
                "	init(\"StdPlace5\", 99999999);%n" +
                "	;%n" +
                "	%n" +
                "}");
    }
    
    public String getExpectedResult(){
        return expected;
    }

    @Override
    protected SPNPCode createCode() {
        return new SPNPCode();
    }
    
    @Override
    protected SPNPOptions createOptions() {
        var parametersSet = new HashSet<InputParameter>();
        parametersSet.add(new IntegerInputParameter("param1", "prompt1"));
        parametersSet.add(new IntegerInputParameter("param2", "prompt2"));
        parametersSet.add(new DoubleInputParameter("param3", "prompt3"));
        parametersSet.add(new DoubleInputParameter("param4", "prompt4"));
        
        var optionsSet = new HashSet<Option>();
        optionsSet.add(new IntegerTypeOption(OptionKey.IOP_OK_VANLOOP, 5));
        optionsSet.add(new IntegerTypeOption(OptionKey.IOP_ELIMINATION, 125));
        optionsSet.add(new DoubleTypeOption(OptionKey.FOP_SIM_ERROR, 55.46));
        optionsSet.add(new IntegerTypeOption(OptionKey.IOP_SSMETHOD, 666666666));
        optionsSet.add(new ConstantTypeOption(OptionKey.FOP_ABS_RET_M0, ConstantValue.VAL_SPLIT));
        optionsSet.add(new ConstantTypeOption(OptionKey.FOP_SSPRES, ConstantValue.VAL_REPL));
        
        return new SPNPOptions(parametersSet, optionsSet);
    }
    
    @Override
    protected PetriNet createPetriNet() {
        var net = new PetriNet();
        
        var stdPlace1 = new StandardPlace(0, "StdPlace1");
        var stdPlace2 = new StandardPlace(1, "StdPlace2");
        var stdPlace3 = new StandardPlace(2, "StdPlace3");
        var stdPlace4 = new StandardPlace(3, "StdPlace4");
        var stdPlace5 = new StandardPlace(4, "StdPlace5");
        
        stdPlace3.setNumberOfTokens(5);
        stdPlace5.setNumberOfTokens(99999999);
        net.addPlace(stdPlace1);
        net.addPlace(stdPlace2);
        net.addPlace(stdPlace3);
        net.addPlace(stdPlace4);
        net.addPlace(stdPlace5);
        
        var fluidPlace1 = new FluidPlace(0, "FluidPlace1");
        var fluidPlace2 = new FluidPlace(1, "FluidPlace2");
        var fluidPlace3 = new FluidPlace(2, "FluidPlace3");
        var fluidPlace4 = new FluidPlace(3, "FluidPlace4");
        var fluidPlace5 = new FluidPlace(4, "FluidPlace5");
        
        fluidPlace1.setInitialValue(0.123);
        fluidPlace4.setInitialValue(9849.2615);
        fluidPlace5.setInitialValue(546.00001);

        fluidPlace3.setBoundValue(0.5654);
        fluidPlace4.setBoundValue(165.5654);
        fluidPlace5.setBoundValue(99999.00001);
        
        fluidPlace4.addBreakValue(123.01);
        fluidPlace4.addBreakValue(456.02);
        fluidPlace4.addBreakValue(789.03);
        fluidPlace4.addBreakValue(999.99);
        fluidPlace4.addBreakValue(987.789);
        fluidPlace5.addBreakValue(1.1);
        fluidPlace5.addBreakValue(7.7);

        net.addPlace(fluidPlace1);
        net.addPlace(fluidPlace2);
        net.addPlace(fluidPlace3);
        net.addPlace(fluidPlace4);
        net.addPlace(fluidPlace5);

        var constantTransitionProbability = new ConstantTransitionProbability(0.75);
        var immediateTransition1 = new ImmediateTransition(1, "ImmediateTransition1", 1, null, constantTransitionProbability);
        net.addTransition(immediateTransition1);

        net.addArc(new StandardArc(1, ArcDirection.Input, stdPlace1, immediateTransition1));

        return net;
    }
}
