package cz.muni.fi.spnp.core.transformators;

import cz.muni.fi.spnp.core.models.PetriNet;

public interface Transformator {
    String transform(PetriNet petriNet);
}
