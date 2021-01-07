package cz.muni.fi.spnp.core.transformators.spnp;

import cz.muni.fi.spnp.core.transformators.spnp.options.Option;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;

import java.util.Set;

public class SPNPOptions {

    private final Set<InputParameter> inputParameters;
    private final Set<Option> options;

    public SPNPOptions(Set<InputParameter> inputParameters, Set<Option> options) {
        this.inputParameters = inputParameters;
        this.options = options;
    }

    public Set<InputParameter> getInputParameters() {
        return inputParameters;
    }

    public Set<Option> getOptions() {
        return options;
    }

}
