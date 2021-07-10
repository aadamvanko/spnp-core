package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPFluidPlace;
import cz.muni.fi.spnp.core.transformators.spnp.elements.SPNPStandardPlace;
import org.apache.commons.lang3.math.NumberUtils;

public class PlaceVisitorImpl extends Visitor implements PlaceVisitorSPNP {

    @Override
    public void visit(FluidPlace fluidPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(fluidPlace.getCommentary().getLineCommentary());
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

        stringBuilder.append(definition);
    }

    @Override
    public void visit(SPNPFluidPlace spnpFluidPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(spnpFluidPlace.getCommentary().getLineCommentary());
        definition.append(String.format("fplace(\"%s\");", spnpFluidPlace.getName()));
        definition.append(System.lineSeparator());

        if (isGreaterThan(spnpFluidPlace.getInitialValueExpression(), 0)) {
            definition.append(String.format("finit(\"%s\", %s);", spnpFluidPlace.getName(), spnpFluidPlace.getInitialValueExpression()));
            definition.append(System.lineSeparator());
        }
        if (isGreaterThan(spnpFluidPlace.getBoundValueExpression(), 0.0)) {
            definition.append(String.format("fbound(\"%s\", %s);", spnpFluidPlace.getName(), spnpFluidPlace.getBoundValueExpression()));
            definition.append(System.lineSeparator());
        }
        if (!spnpFluidPlace.getBreakValuesExpressions().isEmpty()) {
            for (var breakValue : spnpFluidPlace.getBreakValuesExpressions()) {
                definition.append(String.format("fbreak(\"%s\", %s);", spnpFluidPlace.getName(), breakValue));
                definition.append(System.lineSeparator());
            }
        }

        stringBuilder.append(definition);
    }

    @Override
    public void visit(StandardPlace standardPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(standardPlace.getCommentary().getLineCommentary());
        definition.append(String.format("place(\"%s\");", standardPlace.getName()));
        definition.append(System.lineSeparator());

        if (standardPlace.getNumberOfTokens() > 0) {
            definition.append(String.format("init(\"%s\", %d);", standardPlace.getName(), standardPlace.getNumberOfTokens()));
            definition.append(System.lineSeparator());
        }

        stringBuilder.append(definition);
    }

    @Override
    public void visit(SPNPStandardPlace spnpStandardPlace) {
        StringBuilder definition = new StringBuilder();

        definition.append(spnpStandardPlace.getCommentary().getLineCommentary());
        definition.append(String.format("place(\"%s\");", spnpStandardPlace.getName()));
        definition.append(System.lineSeparator());

        if (!spnpStandardPlace.getNumberOfTokensExpression().equals("0")) {
            definition.append(String.format("init(\"%s\", %s);", spnpStandardPlace.getName(), spnpStandardPlace.getNumberOfTokensExpression()));
            definition.append(System.lineSeparator());
        }

        stringBuilder.append(definition);
    }

    private boolean isGreaterThan(String possibleDouble, double other) {
        if (!NumberUtils.isCreatable(possibleDouble)) {
            return false;
        }

        return NumberUtils.createDouble(possibleDouble) > other;
    }
}