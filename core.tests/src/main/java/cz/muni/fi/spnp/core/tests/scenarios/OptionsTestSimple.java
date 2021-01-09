package cz.muni.fi.spnp.core.tests.scenarios;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.tests.TransformatorTest;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.options.ConstantTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.ConstantValue;
import cz.muni.fi.spnp.core.transformators.spnp.options.DoubleTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.IntegerTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.Option;
import cz.muni.fi.spnp.core.transformators.spnp.options.OptionKey;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.DoubleInputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.IntegerInputParameter;
import java.util.HashSet;


public class OptionsTestSimple extends TransformatorTest{

    public OptionsTestSimple(){
        super();
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
        return new PetriNet();
    }
}
