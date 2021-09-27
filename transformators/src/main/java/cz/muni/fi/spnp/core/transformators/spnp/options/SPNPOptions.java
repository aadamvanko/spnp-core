package cz.muni.fi.spnp.core.transformators.spnp.options;

import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SPNPOptions {

    private final Set<InputParameter> inputParameters;
    private final List<Option> options;

    public SPNPOptions(Set<InputParameter> inputParameters, List<Option> options) {
        this.inputParameters = inputParameters;
        this.options = options;
    }

    public Set<InputParameter> getInputParameters() {
        return inputParameters;
    }

    public List<Option> getSimulationOptions() {
        return Collections.unmodifiableList(filterOptionsByCategory(OptionCategory.SIMULATION));
    }

    public List<Option> getAnalysisOptions() {
        return Collections.unmodifiableList(filterOptionsByCategory(OptionCategory.ANALYSIS));
    }

    public List<Option> getIntermediateOptions() {
        return Collections.unmodifiableList(filterOptionsByCategory(OptionCategory.INTERMEDIATE));
    }

    public List<Option> getMiscellaneousOptions() {
        return Collections.unmodifiableList(filterOptionsByCategory(OptionCategory.MISCELLANEOUS));
    }

    private List<Option> filterOptionsByCategory(OptionCategory optionCategory) {
        return options.stream()
                .filter(option -> option.getKey().getCategory() == optionCategory)
                .collect(Collectors.toList());
    }

    public List<Option> getOptions() {
        return options;
    }

}
