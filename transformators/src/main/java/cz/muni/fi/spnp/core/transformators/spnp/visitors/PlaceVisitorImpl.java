package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.PlaceVisitor;

public class PlaceVisitorImpl extends Visitor implements PlaceVisitor {

    @Override
    public void visit(FluidPlace fluidPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("fplace(\"%s\");", fluidPlace.getName()));
        definition.append(System.lineSeparator());

        if (fluidPlace.getInitialValue() > 0) {
            definition.append(String.format("finit(\"%s\", %s);", fluidPlace.getName(), formatDouble(fluidPlace.getInitialValue())));
            definition.append(System.lineSeparator());
        }
        if (fluidPlace.getBoundValue() > 0.0) {
            definition.append(String.format("fbound(\"%s\", %s);", fluidPlace.getName(), formatDouble(fluidPlace.getBoundValue())));
            definition.append(System.lineSeparator());
        }
        if (!fluidPlace.getBreakValues().isEmpty()) {
            for (var breakValue : fluidPlace.getBreakValues()) {
                definition.append(String.format("fbreak(\"%s\", %s);", fluidPlace.getName(), formatDouble(breakValue)));
                definition.append(System.lineSeparator());
            }
        }

        stringBuilder.append(definition.toString());
    }

    @Override
    public void visit(StandardPlace standardPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(String.format("place(\"%s\");", standardPlace.getName()));
        definition.append(System.lineSeparator());

        if (standardPlace.getNumberOfTokens() > 0) {
            definition.append(String.format("init(\"%s\", %d);", standardPlace.getName(), standardPlace.getNumberOfTokens()));
            definition.append(System.lineSeparator());
        }

        stringBuilder.append(definition.toString());
    }
}