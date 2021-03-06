module spnp.core.transformators {
    requires spnp.core.models;
    requires org.apache.commons.lang3;

    exports cz.muni.fi.spnp.core.transformators.spnp;
    exports cz.muni.fi.spnp.core.transformators.spnp.code;
    exports cz.muni.fi.spnp.core.transformators.spnp.options;
    exports cz.muni.fi.spnp.core.transformators.spnp.parameters;
    exports cz.muni.fi.spnp.core.transformators.spnp.distributions;
    exports cz.muni.fi.spnp.core.transformators.spnp.variables;
    exports cz.muni.fi.spnp.core.transformators.spnp.elements;
    exports cz.muni.fi.spnp.core.transformators.spnp.elements.probabilities;
}