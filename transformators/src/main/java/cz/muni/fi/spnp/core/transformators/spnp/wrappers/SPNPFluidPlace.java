package cz.muni.fi.spnp.core.transformators.spnp.wrappers;

import cz.muni.fi.spnp.core.models.places.FluidPlace;

public class SPNPFluidPlace implements Definable, Declarable {

    private FluidPlace fluidPlace;

    SPNPFluidPlace(FluidPlace fluidPlace) {
        this.fluidPlace = fluidPlace;
    }

    @Override
    public String getDeclaration() {
        return null;
    }

    @Override
    public String getDefinition() {
        return null;
    }

}
